package com.app.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // at run time a table will be created with name as user
@Table(name = "tbl_user") // This will customize the table name with tbl_user
public class User {
	// user table should have primary key
	// every JPA entity must have a primary key. It is compulsory.
	// If you don’t define @Id, you will get:
//org.hibernate.AnnotationException: No identifier specified for entity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Every time a new row is inserted, the database increases the
														// ID by 1 automatically.
	// IDENTITY means the database generates the primary key value automatically
	// using auto-increment.
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "user_email", unique = true)
	private String email; // column name will be same as propertyname

	@Column(name = "user_pwd")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "user_phone")
	private String phoneNumber;

	@Column(name = "user_reg_time")
	private LocalDateTime registerTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_role")
	private UserRole role;

	@Enumerated(EnumType.STRING)
	@Column(name = "acct_status")
	private UserAccountStatus accountStatus;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> address = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Orders> orders = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Payment> payments = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Cart cart;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(LocalDateTime registerTime) {
		this.registerTime = registerTime;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UserAccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(UserAccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public User(String email, String password, String firstName, String lastName, String phoneNumber,
			LocalDateTime registerTime, UserRole role, UserAccountStatus accountStatus, List<Address> address,
			List<Orders> orders, List<Payment> payments, List<Review> reviews, Cart cart) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.registerTime = registerTime;
		this.role = role;
		this.accountStatus = accountStatus;
		this.address = address;
		this.orders = orders;
		this.payments = payments;
		this.reviews = reviews;
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", registerTime=" + registerTime
				+ ", role=" + role + ", accountStatus=" + accountStatus + ", address=" + address + ", orders=" + orders
				+ ", payments=" + payments + ", reviews=" + reviews + ", cart=" + cart + "]";
	}

}
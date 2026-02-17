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
@Entity// at run time a table will be created with name as user
@Table(name="tbl_user") //This will customize the table name with tbl_user
public class User {
	//user table should have primary key
	//every JPA entity must have a primary key. It is compulsory.
	//If you don’t define @Id, you will get:
//org.hibernate.AnnotationException: No identifier specified for entity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Every time a new row is inserted, the database increases the ID by 1 automatically.
	//IDENTITY means the database generates the primary key value automatically using auto-increment.
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "user_email",unique = true)
	private String email;
	
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
	@Column(name = "acc_status")
	private UserAccountStatus accountStatus;
	
	/***
	 * 
	 */
	
	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
	private List<Address> address=new ArrayList<>();

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private Payment payment;
	
	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
	private List<Orders> orders=new ArrayList<>();
	
	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
	private List<Review> reviews=new ArrayList<>();
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private Cart cart;
	

}

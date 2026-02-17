package com.app.ecommerce.DTO;

import jakarta.validation.constraints.NotNull;

public class AdminDTO {
	
	@NotNull(message="Email is Mandatory")
	private String email;
	
	@NotNull(message="Password is Mandatory")
	private String password;
	
	@NotNull(message="FirstName is Mandatory")
	private String firstName;
	
	
	@NotNull(message="LastName is Mandatory")
	private String lastName;
	
	@NotNull(message="PhoneNumber is Mandatory")
	private String phoneNumber;

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

	public AdminDTO(@NotNull(message = "Email is Mandatory") String email,
			@NotNull(message = "Password is Mandatory") String password,
			@NotNull(message = "FirstName is Mandatory") String firstName,
			@NotNull(message = "LastName is Mandatory") String lastName,
			@NotNull(message = "PhoneNumber is Mandatory") String phoneNumber) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	
	
	public AdminDTO() {}

	@Override
	public String toString() {
		return "AdminDTO [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + "]";
	}
	

}

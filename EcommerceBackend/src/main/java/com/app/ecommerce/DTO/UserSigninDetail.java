package com.app.ecommerce.DTO;

//This is the Data Transfer Object
//No need to write @Entity,@Table...all those annotations
public class UserSigninDetail {
	
	Integer id;
	String firstName;
	String lastName;
	String signInStatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getSignInStatus() {
		return signInStatus;
	}
	public void setSignInStatus(String signInStatus) {
		this.signInStatus = signInStatus;
	}
	public UserSigninDetail(Integer id, String firstName, String lastName, String signInStatus) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.signInStatus = signInStatus;
	}
	public UserSigninDetail() {}
	@Override
	public String toString() {
		return "UserSigninDetail [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", signInStatus="
				+ signInStatus + "]";
	}
	
	

}

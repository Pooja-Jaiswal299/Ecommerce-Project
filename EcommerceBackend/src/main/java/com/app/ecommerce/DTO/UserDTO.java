package com.app.ecommerce.DTO;

public class UserDTO {
	
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public UserDTO(String newPassword) {
		super();
		this.newPassword = newPassword;
	}
	public UserDTO() {}

	@Override
	public String toString() {
		return "UserDTO [newPassword=" + newPassword + "]";
	}
	
	

}

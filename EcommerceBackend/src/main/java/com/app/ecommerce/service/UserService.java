package com.app.ecommerce.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.app.ecommerce.DTO.AdminDTO;
import com.app.ecommerce.DTO.CustomerDTO;
import com.app.ecommerce.DTO.UserDTO;
import com.app.ecommerce.model.User;

@Service
public interface UserService {
	
	
	// Adding Customer
	//insert into user ....values....(....)
	public User addUser(CustomerDTO customer) throws Exception;
	
	//Adding UserAdmin
	public User addUserAdmin(AdminDTO adminUser) throws Exception;
	
	
	//select * from user where emaild= .....
	public User getUserByEmailId(String emailId) throws Exception;
	
	
	public User getUserDetails(Integer userId) throws Exception;
	
	//update user set pass=new...
	public User changePassword(Integer userId,UserDTO customer) throws Exception;
	
	//select * from user
	public List<User> getAllUserDetails() throws Exception;
	
	public String deactivateUser(Integer userId) throws Exception;
	
	
	
	
	
	

}

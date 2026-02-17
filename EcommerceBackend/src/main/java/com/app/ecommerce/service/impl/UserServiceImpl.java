package com.app.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.ecommerce.DTO.CustomerDTO;
import com.app.ecommerce.DTO.AdminDTO;
import com.app.ecommerce.DTO.UserDTO;
import com.app.ecommerce.Repository.UserRepository;
import com.app.ecommerce.model.User;
import com.app.ecommerce.model.UserAccountStatus;
import com.app.ecommerce.model.UserRole;
import com.app.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService{

  

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


 
	
	
	public User addUser(CustomerDTO customer) throws Exception{
	
		User newCustomer=new User();
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setPassword(customer.getPassword());
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setPhoneNumber(customer.getPhoneNumber());
		newCustomer.setRole(UserRole.ROLE_USER);
		newCustomer.setRegisterTime(LocalDateTime.now());
		newCustomer.setAccountStatus(UserAccountStatus.ACTIVE);
		
		
		//Verify the given customer already exists or not
		Optional<User> userObj=userRepository.findByEmail(customer.getEmail());
		//select * from user where email=customer.getEmail() ==> REsultset convert into User
		if(userObj.isPresent()) {
			throw new RuntimeErrorException(null, "Email Already Exists");
		}
		
		return userRepository.save(newCustomer);
		//save ==> insert into user values (........);
		//save ==> update user set....
		
		
	
	}

	@Override
	public User changePassword(Integer userId, UserDTO customer) throws Exception {
		
		User userObj=userRepository.findById(userId).orElseThrow(() -> new Exception("User Not Found"));
		//findById ==> select * from user where userid=userId
		if(customer.getNewPassword().length()>=5) {
			userObj.setPassword(passwordEncoder.encode(customer.getNewPassword()));
			return userRepository.save(userObj);
			//update user set password=newpassword where userid=userId
		}
		else
		{
			throw new RuntimeErrorException(null, "Please Provide a Valid Password");
		}
	}




	@Override
	public User addUserAdmin(AdminDTO customer) throws Exception {

		User newCustomer=new User();
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setPassword(customer.getPassword());
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setPhoneNumber(customer.getPhoneNumber());
		newCustomer.setRole(UserRole.ROLE_ADMIN);
		newCustomer.setRegisterTime(LocalDateTime.now());
		newCustomer.setAccountStatus(UserAccountStatus.ACTIVE);
		
		//Verify the given customer already exists or not
		Optional<User> userObj=userRepository.findByEmail(customer.getEmail());
		//select * from user where email=customer.getEmail() ==> REsultset convert into User
		if(userObj.isPresent()) {
			throw new RuntimeErrorException(null, "Email Already Exists");
		}
		
		return userRepository.save(newCustomer);
		//save ==> insert into user values (........);
		//save ==> update user set....
	}





	@Override
	public User getUserByEmailId(String emailId) throws Exception {
		return userRepository.findByEmail(emailId).orElseThrow(()-> new Exception("Email Id Not Found"));
	}



	@Override
	public User getUserDetails(Integer userId) throws Exception {
		return userRepository.findById(userId).orElseThrow(()-> new Exception("User Id Not Found"));
	}


	@Override
	public List<User> getAllUserDetails() throws Exception {
		return userRepository.findAll();
		//findAll ==> select * from user; //Result will be converted List<User>
	}

	@Override
	public String deactivateUser(Integer userId) throws Exception {
		User existingUser=userRepository.findById(userId).orElseThrow(()-> new Exception("User Id Not Found"));
		existingUser.setAccountStatus(UserAccountStatus.DEACTIVE);
		userRepository.save(existingUser);
		return "Account is Deactived Sucessfully";
		
	}
	

}
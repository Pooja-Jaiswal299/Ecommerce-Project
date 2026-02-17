package com.app.ecommerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecommerce.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	//With UserRepository.java ==> we can perform all CRUD Operations==> Insert,Update,Delete

	/*Derived Query 
	 * 
	 *  Developer will write only abstract method and framework implement
	 */
	
	public Optional<User> findByEmail(String email);
	//select * from tbl_user where user_email=email
	//Resultset Converted into Optional<User>
	
	
	
}


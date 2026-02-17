package com.app.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ecommerce.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	//For a user we want to get Addresses ==> Query
	
	/*
	 * Query ==> Developer will write both abstract method and implementation
	 * aslo at interface layer 
	 * 
	 * 
	 */
	
	@Query("SELECT a from Address a where a.user.userId=:userId")
	List<Address> getUserAddressList(Integer userId);
	

}

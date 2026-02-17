package com.app.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.ecommerce.exception.AddressException;
import com.app.ecommerce.model.Address;

@Service
public interface AddressService {
	
	public Address addAddressToUser(Integer userId,Address address) throws AddressException;
	
	public Address updateAddress(Integer addressId,Address address) throws AddressException;
	
	public void removeAddress(Integer addressId) throws AddressException;
	
	
	public List<Address> getAllUserAddress(Integer userId) throws AddressException;
	

}
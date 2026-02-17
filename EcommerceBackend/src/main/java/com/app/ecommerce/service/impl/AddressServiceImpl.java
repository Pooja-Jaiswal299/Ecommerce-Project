package com.app.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecommerce.Repository.AddressRepository;
import com.app.ecommerce.Repository.UserRepository;
import com.app.ecommerce.exception.AddressException;
import com.app.ecommerce.model.Address;
import com.app.ecommerce.model.User;
import com.app.ecommerce.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Address addAddressToUser(Integer userId, Address address) throws AddressException {
		
		User existingUser=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
		
		Address savedAddress=addressRepository.save(address);
		
		savedAddress.setUser(existingUser); //Address to User
		
		existingUser.getAddress().add(savedAddress);  //User to Address
		
		userRepository.save(existingUser);
		return savedAddress;
		
		
		
		
	}

	@Override
	public Address updateAddress(Integer addressId, Address address) throws AddressException {
		
		Address existingAddress=addressRepository.findById(addressId).orElseThrow(() -> new RuntimeException("User Not Found"));
		
		existingAddress.setCity(address.getCity()); 
		existingAddress.setFlatNo(address.getFlatNo());
		existingAddress.setState(address.getState());
		existingAddress.setZipCode(address.getZipCode());
		existingAddress.setStreet(address.getStreet());
		
		return addressRepository.save(existingAddress);
		
	}

	@Override
	public void removeAddress(Integer addressId) throws AddressException {
		
		Address existingAddress=addressRepository.findById(addressId).orElseThrow(()->new RuntimeException("Address Not Found"));
		
		addressRepository.deleteById(addressId);
		//delete from address where addressid=addressId
	}

	@Override
	public List<Address> getAllUserAddress(Integer userId) throws AddressException {
		User existingUser=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
		return addressRepository.getUserAddressList(userId);
	}

}
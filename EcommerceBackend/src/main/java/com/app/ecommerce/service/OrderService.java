package com.app.ecommerce.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.ecommerce.DTO.OrderDTO;
import com.app.ecommerce.exception.OrderException;
import com.app.ecommerce.model.Orders;

@Service
public interface OrderService {
	
	public OrderDTO placeOrder(Integer userId) throws OrderException; //Creating New Order
	//creating new order
//	public Orders upddateOrders(Integer orderId,OrderDTO orderDTO) throws OrderException;
	

	public Orders getOrderDetails(Integer orderId) throws OrderException;
	
	public List<Orders> getAllUserOrders(Integer userId) throws OrderException;
	
	
	public List<Orders> getAllOrders() throws OrderException;
	
	
	public List<Orders> viewAllOrdersByDate(Date orderDate) throws OrderException;
	
	public void deleteOrders(Integer userId,Integer orderId) throws OrderException;
	
	
}

package com.app.ecommerce.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecommerce.Repository.OrderRepository;
import com.app.ecommerce.Repository.PaymentRepository;
import com.app.ecommerce.Repository.UserRepository;
import com.app.ecommerce.exception.OrderException;
import com.app.ecommerce.exception.PaymentException;
import com.app.ecommerce.exception.UserException;
import com.app.ecommerce.model.OrderStatus;
import com.app.ecommerce.model.Orders;
import com.app.ecommerce.model.Payment;
import com.app.ecommerce.model.PaymentMethod;
import com.app.ecommerce.model.PaymentStatus;
import com.app.ecommerce.model.User;
import com.app.ecommerce.service.PaymentService;
@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Payment makePayment(Integer orderId, Integer userId) throws PaymentException {

		User exisUser=userRepository.findById(userId).orElseThrow(
				() -> new UserException("User is Not Availble"));
		
		Orders ordObj=orderRepository.findById(orderId).orElseThrow(
				() -> new OrderException("Order is Not Availble"));
		
		if(ordObj==null) {
			throw new PaymentException("Order not found for the given customer");
		}
		
		Payment payment=new Payment();
		payment.setPaymentAmount(ordObj.getTotalAmount());
		payment.setPaymentDate(LocalDateTime.now());
		payment.setPaymentMethod(PaymentMethod.UPI);
		payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
		payment.setUser(exisUser);
		payment.setOrder(ordObj);
		
		paymentRepository.save(payment);

		ordObj.setOrderStatus(OrderStatus.SHIPPED);
		ordObj.setPayment(payment);
		
		orderRepository.save(ordObj);
		
		exisUser.getPayments().add(payment);
		userRepository.save(exisUser);
		
		return payment;}}
		
	
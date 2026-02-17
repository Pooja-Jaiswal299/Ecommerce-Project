package com.app.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecommerce.DTO.OrderDTO;
import com.app.ecommerce.Repository.CartItemRepository;
import com.app.ecommerce.Repository.CartRepository;
import com.app.ecommerce.Repository.OrderItemRepository;
import com.app.ecommerce.Repository.OrderRepository;
import com.app.ecommerce.Repository.ProductRepository;
import com.app.ecommerce.Repository.UserRepository;
import com.app.ecommerce.exception.OrderException;
import com.app.ecommerce.exception.UserException;
import com.app.ecommerce.model.Cart;
import com.app.ecommerce.model.CartItem;
import com.app.ecommerce.model.OrderItem;
import com.app.ecommerce.model.OrderStatus;
import com.app.ecommerce.model.Orders;
import com.app.ecommerce.model.PaymentStatus;
import com.app.ecommerce.model.User;
import com.app.ecommerce.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	
	

	@Override
	public OrderDTO placeOrder(Integer userId) throws OrderException {
	
		
		User existingUser=userRepository.findById(userId).orElseThrow(
				()-> new UserException("User Not Found in the Database"));
		
		
		Cart userCart=existingUser.getCart();
		
		if(userCart.getTotalAmount()==0) {
			throw new OrderException("Cart is Empty..Add the items First");
		}
		Integer cartId=userCart.getCartId();
		
		Orders newOrd=new Orders();
		
		newOrd.setOrderDate(LocalDateTime.now());
		newOrd.setOrderStatus(OrderStatus.PENDING);
		
		existingUser.getOrders().add(newOrd); //User to Order
		newOrd.setUser(existingUser); //Order to User
		
		userRepository.save(existingUser); 
		orderRepository.save(newOrd); 
		
		//With above two statments user_id will be updated in order table
		  //order_id will be updated in user table
		
		//Now need to save OrderItems
		
		List<OrderItem> orderItems=new ArrayList<>();
		for(CartItem itemDto: userCart.getCartItems()) {
			if(itemDto.getCart().getCartId()==cartId) {
				OrderItem ordItem=new OrderItem();
				ordItem.setProduct(itemDto.getProduct());
				ordItem.setOrderId(newOrd.getOrderId());
				ordItem.setQuantity(itemDto.getQuantity());
				orderItems.add(ordItem);
			}
		}
		
		newOrd.setOrderItem(orderItems); //Order to OrderItems
		newOrd.setTotalAmount(userCart.getTotalAmount());
		orderRepository.save(newOrd);
		
		
		
		//Preparing Return Type
		OrderDTO outputObj=new OrderDTO();
		outputObj.setOrderId(newOrd.getOrderId());
		outputObj.setOrderDate(newOrd.getOrderDate());
		outputObj.setOrderAmount(newOrd.getTotalAmount());
		outputObj.setStatus(OrderStatus.PENDING.toString());
		outputObj.setPaymentStatus(PaymentStatus.PENDING.toString());
		
		
		
		return outputObj;
	}



	@Override
	public Orders getOrderDetails(Integer orderId) throws OrderException {
		return orderRepository.findById(orderId).orElseThrow(()-> new OrderException("Order Not Found in the Database"));
	}

	@Override
	public List<Orders> getAllUserOrders(Integer userId) throws OrderException {
	
		try {
			List<Orders> ordersList=orderRepository.getAllOrdersByUserId(userId);
			if(ordersList.isEmpty()) {
				throw new OrderException("No Orders Found for the User");
			}
			return ordersList;
			
		}catch(Exception ex) {
			throw new OrderException("Exception in Fetching Order Details by User"+ ex.getMessage());
		}
		
	}

	@Override
	public List<Orders> getAllOrders() throws OrderException {
		try {
			List<Orders> ordersList=orderRepository.findAll();
			if(ordersList.isEmpty()) {
				throw new OrderException("No Orders Found in the System");
			}
			return ordersList;
			
		}catch(Exception ex) {
			throw new OrderException("Exception in Fetching Order Details"+ ex.getMessage());
		}
	}

	@Override
	public List<Orders> viewAllOrdersByDate(Date orderDate) throws OrderException {
	
		try {
			List<Orders> ordersList=orderRepository.findByOrderDateGreatThanEqual(orderDate);
			if(ordersList.isEmpty()) {
				throw new OrderException("No Orders Found with the Specified Date");
			}
			return ordersList;
			
		}catch(Exception ex) {
			throw new OrderException("Exception in Fetching Order with the Specified Date"+ ex.getMessage());
		}
		
		
		
	}

	@Override
	public void deleteOrders(Integer userId, Integer orderId) throws OrderException {

  User existingUser=userRepository.findById(userId).
		  orElseThrow(()-> new UserException("No User Found in the Database"));
  Orders existingOrder=orderRepository.findById(orderId).
		  orElseThrow(()-> new UserException("No Orders Found in the Database"));
  
  orderRepository.delete(existingOrder);
		
	}

}
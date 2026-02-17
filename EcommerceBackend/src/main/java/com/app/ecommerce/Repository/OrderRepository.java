package com.app.ecommerce.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ecommerce.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	
	//Need to get Order for A Customer with given orderid ==> 2 inputs ==> CustomerId and OrderId
	
	@Query("SELECT o from Orders o where o.orderId=:orderId  and o.user.userId=:customerId")
	Orders findByOrderIdAndCustomerId(Integer orderId,Integer customerId);
	
	//Need to get Orders based >= orderDate
	@Query("SELECT o from ORDERS o where o.orderDate >= :orderDate")
	List<Orders> findByOrderDateGreatThanEqual(Date orderDate);
	
	//Need to get All orders for a given UserId
	@Query("SELECT o from ORDERS o where o.user.userId=:userId")
	List<Orders> getAllOrdersByUserId(Integer userId);
	
	//Need to get All orders for a given UserName
	@Query("SELECT o from ORDERS o where o.user.email=:userName")
	List<Orders> getAllOrderByUserName(String userName);


}

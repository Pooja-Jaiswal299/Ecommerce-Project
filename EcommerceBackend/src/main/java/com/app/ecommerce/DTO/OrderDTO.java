package com.app.ecommerce.DTO;

import java.time.LocalDateTime;

public class OrderDTO {
	
	private Integer orderId;
	private String status;
	private LocalDateTime orderDate;
	private Double orderAmount;
	private String paymentStatus;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public Double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public OrderDTO(Integer orderId, String status, LocalDateTime orderDate, Double orderAmount, String paymentStatus) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.orderDate = orderDate;
		this.orderAmount = orderAmount;
		this.paymentStatus = paymentStatus;
	}
	
	
	public OrderDTO() {}
	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", status=" + status + ", orderDate=" + orderDate + ", orderAmount="
				+ orderAmount + ", paymentStatus=" + paymentStatus + "]";
	}
	

}

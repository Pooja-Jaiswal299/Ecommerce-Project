package com.app.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_item_id")
	private Integer orderItemId;
	

	@Column(name="ord_qty")
	private Integer quantity;
	
	//One Order → can contain → Many OrderItems
	//One OrderItem → belongs to → One Order
//	@ManyToOne
//	@JoinColumn(name = "order_id")
//	private Orders order;
	
	@Column(name="order_id")
	private Integer orderId;
	
	//order item to product
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	
	

}

package com.app.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_shipping_details")
public class ShippingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shipping_id")
	private Integer shippingId;
	
	@NotNull(message = "Address is Mandatory and it can't be Null")
	@Column(name="address")
	private String address;
	
	@NotNull(message = "City is Mandatory and it can't be Null")
	private String city;
	
	@NotNull(message = "Country is Mandatory and it can't be Null")
	private String country;
	
	@NotNull(message = "PostalCode is Mandatory and it can't be Null")
	private String postalCode;
	
	@NotNull(message = "State is Mandatory and it can't be Null")
	private String state;
	
	@ManyToOne
	@JoinColumn(name="shipper_id")
	private Shipper shipper;
	
	/**
	 * One Order → has one ShippingDetails

One ShippingDetails → belongs to one Order
	 */
	
	@OneToOne
	//@JoinColumn Not mandatory, Hibernate will still create a foreign key column.
	private Orders order;
	
	

}

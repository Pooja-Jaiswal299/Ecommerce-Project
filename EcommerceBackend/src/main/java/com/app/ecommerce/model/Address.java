package com.app.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer addressId;
	
	@NotNull(message = "Flat nnumber is mandatory")
	@Column(name = "flat_no")
	private String flatNo;
	
	@NotNull(message = "Street is mandatory")
	@Column(name = "street")
	private String street;
	
	@Size(max = 20)
	@NotNull(message = "city is mandatory")
	@Column(name = "city")
	private String city;
	
	@Size(max = 20)
	@NotNull(message = "state is mandatory")
	@Column(name = "state")
	private String state;
	
	@NotNull(message = "zipcode is mandatory")
	@Column(name = "zipcode")
	private String zipCode;
	
	//Many records of THIS entity are related to ONE record of another user entity.
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}

package com.app.ecommerce.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Integer paymentId;
	
	@NotNull(message="Payment Amount is Mandatory and cant be Null")
	@Column(name="payment_amount")
	private double paymentAmount;
	
	@Column(name="payment_date")
	private LocalDateTime paymentDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_type")
	private PaymentMethod paymentMethod;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_status")
	private PaymentStatus paymentStatus;
	
	@OneToOne
	@JoinColumn(name="order_id")
	private Orders order;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	


}

package com.app.ecommerce.service;

import com.app.ecommerce.exception.PaymentException;
import com.app.ecommerce.model.Payment;

public interface PaymentService {
	Payment makePayment(Integer orderId,Integer userId) throws PaymentException;

}

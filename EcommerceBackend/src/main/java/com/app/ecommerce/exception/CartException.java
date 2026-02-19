package com.app.ecommerce.exception;

public class CartException extends RuntimeException{
	public CartException() {
		
	}
	
	public CartException(String message) {
		super(message);
	}

}

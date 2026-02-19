package com.app.ecommerce.service;

import org.springframework.stereotype.Service;

import com.app.ecommerce.exception.CartException;
import com.app.ecommerce.model.Cart;

@Service
public interface CartService {
	
	public Cart addProductToCart(Integer userId,Integer productId) throws CartException;
	
	public Cart increaseProductQuantity(Integer userId, Integer productId) throws CartException;
	
	public Cart decreaseProductQuantity(Integer userId, Integer productId) throws CartException;
	
	public void removeProductFromCart(Integer userId, Integer productId) throws CartException;
	
	public void removeAllProductsFromCart(Integer cartId) throws CartException;
	
	public Cart getAllCartProducts(Integer cartId) throws CartException;
}




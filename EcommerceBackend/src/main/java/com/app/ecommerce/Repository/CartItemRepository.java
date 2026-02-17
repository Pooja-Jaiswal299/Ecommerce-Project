package com.app.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ecommerce.model.CartItem;

import jakarta.transaction.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	
	@Transactional
	@Query("DELETE FROM CartItem ci WHERE CI.product.productId=:productId AND CI.cart.cartId=:cartId")
	public void removeProductFromCart(Integer cartId,Integer productId);
	
	@Transactional
	@Query("DELETE FROM CARTITEM ci where CI.cart.cartId=:cartId")
	public void removeAllProductsFromCart(Integer cartId);
	
	
	

}

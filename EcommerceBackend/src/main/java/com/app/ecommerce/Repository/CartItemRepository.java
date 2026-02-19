package com.app.ecommerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ecommerce.model.Cart;
import com.app.ecommerce.model.CartItem;

import jakarta.transaction.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	@Modifying
	@Transactional
	@Query("DELETE FROM CartItem ci WHERE ci.product.productId=:productId AND CI.cart.cartId=:cartId")
	public void removeProductFromCart(Integer cartId,Integer productId);
	@Modifying
	@Transactional
	@Query("DELETE FROM CartItem ci where ci.cart.cartId=:cartId")
	public void removeAllProductsFromCart(Integer cartId);
	
    Optional<CartItem> findByCartAndProduct_ProductId(Cart cart, Integer productId);

	
	
	

}

package com.app.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecommerce.Repository.CartItemRepository;
import com.app.ecommerce.Repository.CartRepository;
import com.app.ecommerce.Repository.ProductRepository;
import com.app.ecommerce.Repository.UserRepository;
import com.app.ecommerce.exception.CartException;
import com.app.ecommerce.exception.ProductException;
import com.app.ecommerce.exception.UserException;
import com.app.ecommerce.model.Cart;
import com.app.ecommerce.model.CartItem;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import com.app.ecommerce.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	/*
	 * System check karta hai:
	 * 
	 * pooja exist karti hai?
	 * 
	 * Product exist karta hai?
	 * 
	 * Cart already hai ya naya banana hai?
	 * 
	 * Product pehle se cart me hai kya?
	 * 
	 * User (1) -------- (1) Cart Cart (1) -------- (Many) CartItem CartItem (Many)
	 * -------- (1) Product Matlab:
	 * 
	 * Ek user ka ek cart
	 * 
	 * Ek cart me multiple cart items
	 * 
	 * Har cart item ek product ko represent karta hai 1. Validate user 2. Validate
	 * product 3. Fetch or create cart 4. Fetch cart item if exists 5. Update
	 * quantity or create new 6. Recalculate total 7. Save cart
	 */

	@Override
	public Cart addProductToCart(Integer userId, Integer productId) throws CartException {
		
		Product exisProd=productRepository.findById(productId).orElseThrow(
				() -> new ProductException("Product is Not Availble in Stock"));
		
		User exisUser=userRepository.findById(userId).orElseThrow(
				() -> new UserException("User is Not Availble"));
		
		//Below code will verify Product is Already available in cart. If availble we will throw exception
		if(exisUser.getCart()!=null) {
			Cart userCart=exisUser.getCart();
			
			List<CartItem> cartItems=userCart.getCartItems();
			if(cartItems!=null) {
				for(int i=0;i< cartItems.size() ; i++) {
					if(cartItems.get(i).getProduct().getProductId()==productId && 
							cartItems.get(i).getCart().getCartId()== userCart.getCartId()) {
						throw new CartException("Already Product Exists in Cart  You can Either Decrease or Increase");
						
					}
				}
			}
			
			CartItem cartItem=new CartItem();
			cartItem.setProduct(exisProd);
			cartItem.setQuantity(1);
			cartItem.setCart(userCart);
			userCart.getCartItems().add(cartItem);
			
			userCart.setTotalAmount(calculateCartTotal(cartItems));
			return cartRepository.save(userCart);
			
		}else
		{
			//First time User is adding Product to the Cart
			
			Cart newCart=new Cart();
			newCart.setUser(exisUser);
			exisUser.setCart(newCart);
			
			CartItem cartItem=new CartItem();
			cartItem.setProduct(exisProd);
			cartItem.setQuantity(1);
			
			newCart.getCartItems().add(cartItem);
			cartItem.setCart(newCart);
			
			newCart.setTotalAmount(calculateCartTotal(newCart.getCartItems()));
			userRepository.save(exisUser);
			return exisUser.getCart();
		}
		
		
		
		
		
	}

	public double calculateCartTotal(List<CartItem> cartItems) {
		double totalAmt=0.0;
		for(CartItem item:cartItems) {
			double itemPrice=item.getProduct().getPrice();
			int itemQty=item.getQuantity();
			totalAmt += (itemPrice * itemQty);
		}
		return totalAmt;
	}
	@Override
	public Cart increaseProductQuantity(Integer userId, Integer productId) throws CartException {
		
		
		User exisUser=userRepository.findById(userId).orElseThrow(
				() -> new UserException("User is Not Availble"));
		
		if(exisUser.getCart() == null) {
			throw new UserException("User Cart is Not available");
		}
		
		Cart userCart=exisUser.getCart();
		List<CartItem> cartItems=userCart.getCartItems();
		
		//Find the Product from Cart which should match with productId and increase 
		// the quantity to the matched product.
		CartItem cartItemToUpdate=cartItems.stream()
				.filter(item -> item.getProduct().getProductId().equals(productId)
			&& item.getCart().getCartId().equals(userCart.getCartId()) 
						).findFirst()
				.orElseThrow(() -> new CartException("Cart Item Not Found"));

		//AFter match found , then we can increase the quantity
		int qty=cartItemToUpdate.getQuantity();
		cartItemToUpdate.setQuantity(qty+1);
		cartItemRepository.save(cartItemToUpdate);
		
		userCart.setCartItems(cartItems);
		userCart.setTotalAmount(calculateCartTotal(cartItems));
		cartRepository.save(userCart);
		
		
		
		
		return userCart;
	}

	@Override
	public Cart decreaseProductQuantity(Integer userId, Integer productId) throws CartException {

		
		User exisUser=userRepository.findById(userId).orElseThrow(
				() -> new UserException("User is Not Availble"));
		
		if(exisUser.getCart() == null) {
			throw new UserException("User Cart is Not available");
		}
		
		Cart userCart=exisUser.getCart();
		List<CartItem> cartItems=userCart.getCartItems();
		
		//Find the Product from Cart which should match with productId and increase 
		// the quantity to the matched product.
		CartItem cartItemToUpdate=cartItems.stream()
				.filter(item -> item.getProduct().getProductId().equals(productId)
			&& item.getCart().getCartId().equals(userCart.getCartId()) 
						).findFirst()
				.orElseThrow(() -> new CartException("Cart Item Not Found"));

		//AFter match found , then we can increase the quantity
		int qty=cartItemToUpdate.getQuantity();
		if(qty==1) {
			throw new CartException("Product Cant be Further Decrease....");
		}
		if (qty > 1) {
			cartItemToUpdate.setQuantity(qty - 1);
			cartItemRepository.save(cartItemToUpdate);

			userCart.setCartItems(cartItems);
			userCart.setTotalAmount(calculateCartTotal(cartItems));
			cartRepository.save(userCart);
		} else {
			cartItems.remove(cartItemToUpdate);
			userCart.setCartItems(cartItems);
			userCart.setTotalAmount(calculateCartTotal(cartItems));
			cartRepository.save(userCart);
		}
		
		
		
		
		return userCart;
	}

	@Override
	public void removeProductFromCart(Integer cartId, Integer productId) throws CartException {
		
		Cart exisCart=cartRepository.findById(cartId).
				orElseThrow(()-> new CartException("Cart Not Found"));
		
		cartItemRepository.removeProductFromCart(cartId, productId);
		
		List<CartItem> list=exisCart.getCartItems();
		exisCart.setTotalAmount(calculateCartTotal(list));
		cartRepository.save(exisCart);
		
		
	}

	@Override
	public void removeAllProductsFromCart(Integer cartId) throws CartException {
		Cart exisCart=cartRepository.findById(cartId).
				orElseThrow(()-> new CartException("Cart Not Found"));
		
		cartItemRepository.removeAllProductsFromCart(cartId);
		exisCart.setTotalAmount(0.0);
		cartRepository.save(exisCart);
		
	}

	@Override
	public Cart getAllCartProducts(Integer cartId) throws CartException {
		
		Cart exisCart=cartRepository.findById(cartId).
				orElseThrow(()-> new CartException("Cart Not Found"));
		
		List<CartItem> cartItems=exisCart.getCartItems();
		List<Product> products=new ArrayList<>();
		
		for(CartItem cartItem:cartItems) {
			if(cartItem.getCart().getCartId() ==cartId) {
				Product prod=cartItem.getProduct();
				products.add(prod);
			}
		}
		
		if(products.isEmpty()) {
			throw new ProductException("Cart Is Empty");
		}
		return exisCart;
	}

}
package com.app.ecommerce.service;

import java.util.List;

import com.app.ecommerce.DTO.ProductDTO;
import com.app.ecommerce.exception.ProductException;
import com.app.ecommerce.model.Product;

public interface ProductService {
	
	public Product addProduct(Product productObj) throws ProductException;
	
	public Product updateProduct(Integer productId,ProductDTO productdto) throws ProductException;

	public List<Product> getAllProducts() throws ProductException;
	
	public List<Product> getAllProducts(String prdName) throws ProductException;
	
	public List<Product> getAllProductsByCategory(String categoryName) throws ProductException;
	
	public List<Product> getAllProducts(String keyword,String sortDirection,String sortBy) throws ProductException;
	
	public Product getSingleProduct(Integer productId) throws ProductException;
	
	public void removeProduct(Integer productId) throws ProductException;


}

package com.app.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecommerce.DTO.ProductDTO;
import com.app.ecommerce.Repository.ProductRepository;
import com.app.ecommerce.exception.OrderException;
import com.app.ecommerce.exception.ProductException;
import com.app.ecommerce.model.Orders;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import com.app.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product productObj) throws ProductException {
		
			
			// Check if product already exists (if ID is provided)
		    if (productObj.getProductId() != null) {
		        Optional<Product> existingProduct =
		                productRepository.findById(productObj.getProductId());

		        if (existingProduct.isPresent()) {
		            throw new ProductException("Product Already Exists");
		        }
		    }

		    Product newProduct = new Product();
		    newProduct.setName(productObj.getName());
		    newProduct.setDescription(productObj.getDescription());
		    newProduct.setPrice(productObj.getPrice());
		    newProduct.setCategory(productObj.getCategory());
		    newProduct.setImageUrl(productObj.getImageUrl());
		    newProduct.setAvailble(productObj.isAvailble());

		    return productRepository.save(newProduct);
		}
	

	@Override
	public Product updateProduct(Integer productId, ProductDTO productdto) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProducts() throws ProductException {
		try {
			List<Product> productList=productRepository.findAll();
			if(productList.isEmpty()) {
				throw new OrderException("No Orders Found in the System");
			}
			return productList;
			
		}catch(Exception ex) {
			throw new OrderException("Exception in Fetching Order Details"+ ex.getMessage());
		}
	}

	@Override
	public List<Product> getAllProducts(String prdName) throws ProductException {

		return null;
	}

	@Override
	public List<Product> getAllProductsByCategory(String categoryName) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProducts(String keyword, String sortDirection, String sortBy) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getSingleProduct(Integer productId) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeProduct(Integer productId) throws ProductException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
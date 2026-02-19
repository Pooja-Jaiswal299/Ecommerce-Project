package com.app.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository prodRepo;

	@Override
	public Product addProduct(Product productObj) throws ProductException {
		if(productObj==null) {
			throw new ProductException("Product Input is Empty");
		}
		return prodRepo.save(productObj); //Insert the data into Product Table
	}

	@Override
	public Product updateProduct(Integer productId, ProductDTO productdto) throws ProductException {

		Product dbProduct=prodRepo.findById(productId).
						orElseThrow(()-> new ProductException("ProductID Not Found"));
		
		dbProduct.setDescription(productdto.getDescription());
		dbProduct.setImageUrl(productdto.getImageUrl());
		dbProduct.setPrice(productdto.getPrice());
		dbProduct.setCategory(productdto.getCategory());
		dbProduct.setName(productdto.getName());
		
		
		return prodRepo.save(dbProduct);//This will trigger update statement
	}

	@Override
	public List<Product> getAllProducts() throws ProductException {
		List<Product> prdList= prodRepo.findAll();
		if(prdList.isEmpty()) {
			throw new ProductException("No Products available in Database");
		}
		return prdList;
	}

	@Override
	public List<Product> getAllProducts(String prdName) throws ProductException {
		List<Product> prdList= prodRepo.findByName(prdName);
		if(prdList.isEmpty()) {
			throw new ProductException("No Products available in Database");
		}
		return prdList;
	}

	@Override
	public List<Product> getAllProductsByCategory(String categoryName) throws ProductException {
		List<Product> prdList= prodRepo.getProductsByCategory(categoryName);
		if(prdList.isEmpty()) {
			throw new ProductException("No Products available in Database");
		}
		return prdList;
	}

	@Override
	public List<Product> getAllProducts(String keyword, String sortDirection, String sortBy) throws ProductException {
		Sort sort=Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC);
		 List<Product>  products;
		if(keyword != null) {
			products=prodRepo.findAllByNameContainingIgnoreCase(keyword, sort);
		}
		else
		{
			products=prodRepo.findAll(sort);
		}
		if(products.isEmpty()) {
			throw new ProductException("No Products available in Database");
		}
		return products;
	}

	@Override
	public Product getSingleProduct(Integer productId) throws ProductException {
		return prodRepo.findById(productId).
				orElseThrow(()-> new ProductException("ProductID Not Found"));
	}

	@Override
	public void removeProduct(Integer productId) throws ProductException {
		Product dbProd=prodRepo.findById(productId).
				orElseThrow(()-> new ProductException("ProductID Not Found"));
		prodRepo.delete(dbProd);
		
	}

}
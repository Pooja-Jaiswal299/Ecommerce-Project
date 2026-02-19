package com.app.ecommerce.Repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	public List<Product> findByName(String name);
	//As its derived Query , we no need to implement this.
	
	@Query("SELECT p from Product p where p.category like '%categoryName%'")
	public List<Product> getProductsByCategory(String categoryName);
	
	public List<Product> findAllByNameContainingIgnoreCase(String keyword,Sort sortOrder);
	//select * from product where name =keyword order by name sortorder
}

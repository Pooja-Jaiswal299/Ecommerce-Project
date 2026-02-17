package com.app.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;
	
	@NotNull(message="Product Name is Mandatory and cant be Null")
	@Column(name="prod_name")
	private String name;
	
	@NotNull(message="Product Description is Mandatory and cant be Null")
	@Column(name="prod_description")
	@Size(min=10,max=50)
	private String description;
	
	
	@NotNull(message="Image URL is Mandatory and cant be Null")
	@Column(name="img_url")
	private String imageUrl;
	
	
	@NotNull(message="Product Price is Mandatory and cant be Null")
	@Column(name="prd_price")
	private Double price;
	
	@NotNull(message="Product Category is Mandatory and cant be Null")
	@Column(name="category_name")
	private String category;
	
	@Column(name="is_availble")
	private boolean isAvailble=true;
	
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderItem> orderItem=new ArrayList<>();

	
	//OnetoMany  with Reviews
		@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
		private List<Review> reviews=new ArrayList<>();

		//cartitems not added here why because One Product can be referenced by many CartItems. not need 
		
}

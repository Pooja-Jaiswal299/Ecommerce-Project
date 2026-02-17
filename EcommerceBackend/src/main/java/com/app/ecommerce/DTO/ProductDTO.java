package com.app.ecommerce.DTO;

public class ProductDTO {
	
	private String name;
	private String description;
	private String imageUrl;
	private Double price;
	private String category;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public ProductDTO(String name, String description, String imageUrl, Double price, String category) {
		super();
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
		this.category = category;
	}
	
	public ProductDTO() {}
	@Override
	public String toString() {
		return "ProductDTO [name=" + name + ", description=" + description + ", imageUrl=" + imageUrl + ", price="
				+ price + ", category=" + category + "]";
	}
	
	

}

package com.app.ecommerce.DTO;

public class ShippingDTO {
	
	private String address;
	private String city;
	private String country;
	private String postalCode;
	private String state;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public ShippingDTO(String address, String city, String country, String postalCode, String state) {
		super();
		this.address = address;
		this.city = city;
		this.country = country;
		this.postalCode = postalCode;
		this.state = state;
	}
	
	
	public ShippingDTO() {}
	@Override
	public String toString() {
		return "ShippingDTO [address=" + address + ", city=" + city + ", country=" + country + ", postalCode="
				+ postalCode + ", state=" + state + "]";
	}
	
	
}

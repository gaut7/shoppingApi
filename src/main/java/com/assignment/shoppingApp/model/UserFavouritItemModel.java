package com.assignment.shoppingApp.model;


import java.io.Serializable;

public class UserFavouritItemModel implements Serializable{
	
	private static final long serialVersionUID = -2555283627419899952L;

	private Integer id;
	private UserModel user;
	private ProductModel product;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	
	

}

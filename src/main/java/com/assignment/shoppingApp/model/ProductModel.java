package com.assignment.shoppingApp.model;


import java.io.Serializable;
import java.util.List;

import com.assignment.shoppingApp.dto.StoreDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductModel implements Serializable{
	
	private static final long serialVersionUID = -2555283627419899952L;

	private Integer id;
	private String name;
	private double price;
	private String image;
	private int discount;
	private StoreModel store;
	

	public StoreModel getStore() {
		return store;
	}

	public void setStore(StoreModel store) {
		this.store = store;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
}

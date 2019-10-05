package com.assignment.shoppingApp.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class StoreModel implements Serializable{
	
	private static final long serialVersionUID = -2555283627419899952L;

	private Integer id;
	private String name;
	private String description;
	private String image;
	private ProductSubcategoryModel  productSubcategory;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ProductSubcategoryModel getProductSubcategory() {
		return productSubcategory;
	}

	public void setProductSubcategory(ProductSubcategoryModel productSubcategory) {
		this.productSubcategory = productSubcategory;
	}

}

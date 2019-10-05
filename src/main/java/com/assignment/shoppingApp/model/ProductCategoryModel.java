package com.assignment.shoppingApp.model;

import java.io.Serializable;
import java.util.List;


public class ProductCategoryModel implements Serializable{
	
	private static final long serialVersionUID = -2555283627419899952L;
	private Integer id;
	private String name;
	private List<ProductSubcategoryModel> productSubcategory;

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

	public List<ProductSubcategoryModel> getProductSubcategory() {
		return productSubcategory;
	}

	public void setProductSubcategory(List<ProductSubcategoryModel> productSubcategory) {
		this.productSubcategory = productSubcategory;
	}



}

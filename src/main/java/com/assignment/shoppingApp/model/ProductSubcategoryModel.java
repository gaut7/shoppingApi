package com.assignment.shoppingApp.model;


import java.io.Serializable;


public class ProductSubcategoryModel implements Serializable{
	
	private static final long serialVersionUID = -2555283627419899952L;

	private Integer id;
	private String name;
	private ProductCategoryModel productCategory;

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

	public ProductCategoryModel getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategoryModel productCategory) {
		this.productCategory = productCategory;
	}
	
}

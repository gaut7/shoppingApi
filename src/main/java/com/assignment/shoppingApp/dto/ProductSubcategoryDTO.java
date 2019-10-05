package com.assignment.shoppingApp.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_subcategory")
public class ProductSubcategoryDTO implements Serializable{
	
	private static final long serialVersionUID = -2555283627419899952L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "category_id")
	private ProductCategoryDTO productCategory;

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

	public ProductCategoryDTO getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategoryDTO productCategory) {
		this.productCategory = productCategory;
	}
	
}

package com.assignment.shoppingApp.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDTO implements Serializable{
	
	private static final long serialVersionUID = -2555283627419899952L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval=true,fetch = FetchType.EAGER)
	@JoinColumn(name="product_category_id",nullable=false)
	private List<ProductSubcategoryDTO> productSubcategory;

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

	public List<ProductSubcategoryDTO> getProductSubcategory() {
		return productSubcategory;
	}

	public void setProductSubcategory(List<ProductSubcategoryDTO> productSubcategory) {
		this.productSubcategory = productSubcategory;
	}
	
	

}

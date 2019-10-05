package com.assignment.shoppingApp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.assignment.shoppingApp.exception.AssignmentException;
import com.assignment.shoppingApp.model.ProductModel;

public interface ProductService {
	
	public List<ProductModel>getFavouritItemOFUser(HttpServletRequest request)throws AssignmentException;
    public void addItemToFavourit(HttpServletRequest request,ProductModel productModel)throws AssignmentException;
    public void removeItemFromFavourit(HttpServletRequest request,int productId)throws AssignmentException;
	public List<ProductModel> getProductByName(ProductModel productModel)throws AssignmentException;
	public List<ProductModel> getProductList()throws AssignmentException;
    
}

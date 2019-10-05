package com.assignment.shoppingApp.service;

import java.util.List;

import com.assignment.shoppingApp.exception.AssignmentException;
import com.assignment.shoppingApp.model.ProductModel;
import com.assignment.shoppingApp.model.StoreModel;

public interface StoreService {
	
	List<StoreModel> getStoreList() throws AssignmentException;
	List<ProductModel> getProductByStore(int storeId) throws AssignmentException;
}

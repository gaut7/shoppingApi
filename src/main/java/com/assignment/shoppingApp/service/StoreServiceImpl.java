package com.assignment.shoppingApp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.assignment.shoppingApp.dto.ProductDTO;
import com.assignment.shoppingApp.dto.StoreDTO;
import com.assignment.shoppingApp.exception.AssignmentException;
import com.assignment.shoppingApp.model.ProductModel;
import com.assignment.shoppingApp.model.StoreModel;
import com.assignment.shoppingApp.repository.ProductRepository;
import com.assignment.shoppingApp.repository.StoreRepository;
import com.assignment.shoppingApp.utils.CustomDozerHelper;

@Service
@PropertySources(value = { @PropertySource("classpath:messages.properties"),
		@PropertySource("classpath:exception.properties"),
		@PropertySource("classpath:application.properties") })
public class StoreServiceImpl implements StoreService, EnvironmentAware{
	
	
	private static final Logger LOGGER = Logger.getLogger(StoreServiceImpl.class);

	private Mapper mapper = new DozerBeanMapper();

	private static Environment environment;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@SuppressWarnings("static-access")
	@Override
	public void setEnvironment(final Environment environment) {
		this.environment = environment;
	}

	@Override
	public List<StoreModel> getStoreList() throws AssignmentException{
		
		List<StoreModel> storeListDetail = new ArrayList<StoreModel>();
		try {
			List<StoreDTO> storeList = storeRepository.findAll();
			if(!storeList.isEmpty())
			{
				storeListDetail = CustomDozerHelper.map(mapper, storeList, StoreModel.class);
			}
		}catch (Exception exception) {
			LOGGER.error(exception);
			throw new AssignmentException("Something went wrong"); 
		}
		return storeListDetail;
	}

	@Override
	public List<ProductModel> getProductByStore(int storeId) throws AssignmentException {
		
		List<ProductModel> productList = new ArrayList<ProductModel>();
		
		try {
			List<ProductDTO> products = productRepository.getProductByStoreId(storeId);
			if(!products.isEmpty())
			{
				productList = CustomDozerHelper.map(mapper, products, ProductModel.class);
			}
			
		} catch (Exception exception) {
			LOGGER.error(exception);
			throw new AssignmentException("Something went wrong");
		}
		return productList;
	}

}

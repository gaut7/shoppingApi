package com.assignment.shoppingApp.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.shoppingApp.dto.ProductDTO;
import com.assignment.shoppingApp.dto.UserDTO;
import com.assignment.shoppingApp.dto.UserFavouritItemDTO;
import com.assignment.shoppingApp.exception.AssignmentException;
import com.assignment.shoppingApp.model.ProductModel;
import com.assignment.shoppingApp.model.UserFavouritItemModel;
import com.assignment.shoppingApp.model.UserModel;
import com.assignment.shoppingApp.repository.ProductRepository;
import com.assignment.shoppingApp.repository.UserFavouritItemrepository;
import com.assignment.shoppingApp.utils.CustomDozerHelper;

@RestController
@RequestMapping(path = "product")
@PropertySources(value = { @PropertySource("classpath:messages.properties"),
		@PropertySource("classpath:application.properties") })
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ProductServiceImpl implements ProductService{
	
	private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);

	private Mapper mapper = new DozerBeanMapper();
	
	@Autowired
	private UserFavouritItemrepository userFavouritItemrepository;
	
	@Autowired
	private ProductRepository productRepository;
	

	@Override
	public List<ProductModel> getFavouritItemOFUser(HttpServletRequest request)throws AssignmentException {
		UserModel user = (UserModel) request.getAttribute("user");
		List<ProductModel> productList = new ArrayList<ProductModel>();
		try
		{
			List<ProductDTO> products =  userFavouritItemrepository.getProductListByUserId(user.getId());
			if(!products.isEmpty())
			{
				productList = CustomDozerHelper.map(mapper, products, ProductModel.class);	
			}
		}catch (Exception exception) {
			LOGGER.error(exception);
			throw new AssignmentException("Something went wrong");
		}
		
		return productList;
	}



	@Override
	public void addItemToFavourit(HttpServletRequest request,ProductModel productModel) throws AssignmentException{
		try
		{
			UserFavouritItemDTO userFavouritItemDTO = new UserFavouritItemDTO();
			ProductDTO productDTO = mapper.map(productModel, ProductDTO.class);
			UserModel user = (UserModel) request.getAttribute("user");
			UserDTO userDTO = mapper.map(user, UserDTO.class);
			userFavouritItemDTO.setProduct(productDTO);
			userFavouritItemDTO.setUser(userDTO);
			userFavouritItemrepository.save   (userFavouritItemDTO);
			
		}catch (Exception exception) {
			LOGGER.error(exception);
			throw new AssignmentException("Something went wrong");
		}
	}



	@Override
	public void removeItemFromFavourit(HttpServletRequest request,int productId) throws AssignmentException{
		try
		{
			UserModel user = (UserModel) request.getAttribute("user");
			userFavouritItemrepository.deleteFavouritByProductId(productId,user.getId());
		}catch (Exception exception) {
			LOGGER.error(exception);
			throw new AssignmentException("Something went wrong");
		}
		
	}



	@Override
	public List<ProductModel> getProductByName(ProductModel productModel)throws AssignmentException {
		List<ProductModel> productList = new ArrayList<ProductModel>();
 	    try { 
		  List<ProductDTO> products = productRepository.findByName(productModel.getName());
		  if(!products.isEmpty()) 
		    { 
			  productList = CustomDozerHelper.map(mapper,products, ProductModel.class); 
			}
		  }catch (Exception exception) {
			  LOGGER.error(exception); 
			  throw new AssignmentException("Something went wrong"); 
		  }
		return productList;
	}

	@Override
	public List<ProductModel> getProductList()throws AssignmentException {
		List<ProductModel> productList = new ArrayList<ProductModel>();
 	    try { 
		  List<ProductDTO> products = productRepository.findAll();
		  if(!products.isEmpty()) 
		    { 
			  productList = CustomDozerHelper.map(mapper,products, ProductModel.class); 
			}
		  }catch (Exception exception) {
			  LOGGER.error(exception); 
			  throw new AssignmentException("Something went wrong"); 
		  }
		return productList;
	}

}

package com.assignment.shoppingApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.assignment.shoppingApp.exception.AssignmentException;
import com.assignment.shoppingApp.model.ProductModel;
import com.assignment.shoppingApp.model.ResponseModel;
import com.assignment.shoppingApp.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "product")
@PropertySources(value = { @PropertySource("classpath:messages.properties"),
		@PropertySource("classpath:application.properties") })
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ProductController {
	
private static final Logger LOGGER = Logger.getLogger(StoreController.class);
	
	@Autowired
	private ProductService productService; 
	
	@GetMapping()
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful return product list", response = ResponseModel.class) })
	@ApiOperation(value = "product list.", notes = "This API used to return the list of products.")
	public ResponseEntity<ResponseModel> getProductList() throws AssignmentException {
		LOGGER.info("<------users favourit product list ------>");
		ResponseModel responseModel = ResponseModel.getInstance();
		List<ProductModel> productList = productService.getProductList();
		responseModel.setObject(productList);
		responseModel.setMessage("product list");
		responseModel.setStatus(HttpStatus.OK.toString());
		return new ResponseEntity<>(responseModel, HttpStatus.OK);
	}
	
	@GetMapping("/favourit")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful return favourit item of user", response = ResponseModel.class) })
	@ApiOperation(value = "favourit product list.", notes = "This API used to return the list of favourit item added by user.")
	public ResponseEntity<ResponseModel> getUsersFavouritItem(@Context HttpServletRequest request) throws AssignmentException {
		LOGGER.info("<------users favourit product list ------>");
		ResponseModel responseModel = ResponseModel.getInstance();
		List<ProductModel> productList = productService.getFavouritItemOFUser(request);
		responseModel.setObject(productList);
		responseModel.setMessage("Users Favourit product list");
		responseModel.setStatus(HttpStatus.OK.toString());
		return new ResponseEntity<>(responseModel, HttpStatus.OK);
	}
	
	
	  @DeleteMapping("/favourit/{productId}")
	  @ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			  @ApiResponse(code = 404, message = "Service not found"),
			  @ApiResponse(code = 200, message = "remove favourit item from list", response = ResponseModel.class) })
	  @ApiOperation(value = "", notes = "This API used to remove item from favourit") public
	  ResponseEntity<ResponseModel> removeProduct(@Context HttpServletRequest request,@PathVariable("productId") int productId) throws AssignmentException {
		  LOGGER.info("<------remove favourit item from list ------>"); 
		  ResponseModel responseModel = ResponseModel.getInstance();
		  productService.removeItemFromFavourit(request,productId);
		  responseModel.setMessage("product removed from favourite");
		  responseModel.setStatus(HttpStatus.OK.toString());
		  return new ResponseEntity<>(responseModel, HttpStatus.OK); 
	  }
	  
	  @PostMapping("/favourit")
	  @ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			  @ApiResponse(code = 404, message = "Service not found"),
			  @ApiResponse(code = 200, message = "Add item to users favourit list", response = ResponseModel.class) })
	  @ApiOperation(value = "", notes = "This API used to add product to users account") public
	  ResponseEntity<ResponseModel> addToFavourit(@Context HttpServletRequest request,@RequestBody ProductModel productModel) throws AssignmentException {
		  LOGGER.info("<------remove favourit item from list ------>"); 
		  ResponseModel responseModel = ResponseModel.getInstance();
		  productService.addItemToFavourit(request,productModel);
		  responseModel.setMessage("product removed from favourite"); 
		  responseModel.setStatus(HttpStatus.CREATED.toString());
		  return new ResponseEntity<>(responseModel, HttpStatus.CREATED); 
	  }
	  
	  @PostMapping("/search")
	  @ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			  @ApiResponse(code = 404, message = "Service not found"),
			  @ApiResponse(code = 200, message = "get list of products", response = ResponseModel.class) })
	  @ApiOperation(value = "", notes = "This API used to return list of product by search name") public
	  ResponseEntity<ResponseModel> getProductByProductName(@RequestBody ProductModel productModel) throws AssignmentException {
		  LOGGER.info("<------remove favourit item from list ------>"); 
		  ResponseModel responseModel = ResponseModel.getInstance();
		  List<ProductModel> productList = productService.getProductByName(productModel);
		  responseModel.setMessage("product list fetched successfully"); 
		  responseModel.setObject(productList);
		  responseModel.setStatus(HttpStatus.OK.toString());
		  return new ResponseEntity<>(responseModel, HttpStatus.OK); 
	  }
	 
}

package com.assignment.shoppingApp.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.shoppingApp.constant.AssignmentConstant;
import com.assignment.shoppingApp.dto.ProductDTO;
import com.assignment.shoppingApp.exception.AssignmentException;
import com.assignment.shoppingApp.model.ProductModel;
import com.assignment.shoppingApp.model.ResponseModel;
import com.assignment.shoppingApp.model.StoreModel;
import com.assignment.shoppingApp.model.UserModel;
import com.assignment.shoppingApp.service.StoreService;
import com.assignment.shoppingApp.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "stores")
@PropertySources(value = { @PropertySource("classpath:messages.properties"),
		@PropertySource("classpath:application.properties") })
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class StoreController {
	
    private static final Logger LOGGER = Logger.getLogger(StoreController.class);
	
	@Autowired
	private StoreService storeService;
	
	@GetMapping
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful returned store list", response = ResponseModel.class) })
	@ApiOperation(value = "Store list.", notes = "This API used to return store list.")
	public ResponseEntity<ResponseModel> getStores() throws AssignmentException {
		LOGGER.info("<------Store list executiing ------>");
		ResponseModel responseModel = ResponseModel.getInstance();
		List<StoreModel> storeDetails = storeService.getStoreList();
		responseModel.setObject(storeDetails);
		responseModel.setMessage("Successful store list");
		return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
	}
	
	@GetMapping("/products/{storeId}")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful product list picked", response = ResponseModel.class) })
	@ApiOperation(value = "Product list.", notes = "This API used to pick product for specific store.")
	public ResponseEntity<ResponseModel> getProductByStoreId(@PathVariable("storeId") int storeId) throws AssignmentException {
		LOGGER.info("<------product list controller start execution ------>");
		ResponseModel responseModel = ResponseModel.getInstance();
		List<ProductModel> productList =  storeService.getProductByStore(storeId);
		responseModel.setObject(productList);
		responseModel.setMessage("productlist of stored fetched successfully");
		return new ResponseEntity<>(responseModel, HttpStatus.OK);
	}
	
}

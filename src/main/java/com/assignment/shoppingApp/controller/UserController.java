package com.assignment.shoppingApp.controller;



import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.assignment.shoppingApp.constant.AssignmentConstant;
import com.assignment.shoppingApp.exception.AssignmentException;
import com.assignment.shoppingApp.model.ResponseModel;
import com.assignment.shoppingApp.model.UserModel;
import com.assignment.shoppingApp.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(path = "user")
@PropertySources(value = { @PropertySource("classpath:messages.properties"),
		@PropertySource("classpath:application.properties") })
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class UserController {
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment environment;
	
	
	@PostMapping("/registration")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 201, message = "Successful created", response = ResponseModel.class) })
	@ApiOperation(value = "Register user.", notes = "This API used to register new user.")
	public ResponseEntity<ResponseModel> registerUsers(@RequestBody UserModel user) throws AssignmentException {
		LOGGER.info("<------create user API started ------>");
		ResponseModel responseModel = ResponseModel.getInstance();
		UserModel userModel = userService.createUser(user);
		responseModel.setObject(userModel);
		responseModel.setMessage(environment.getProperty(AssignmentConstant.USER_CREATED_SUCCESS));
		return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful logged in", response = ResponseModel.class) })
	@ApiOperation(value = "Register user.", notes = "This API used to validate user credentials.")
	public ResponseEntity<ResponseModel> login(@RequestBody UserModel user) throws AssignmentException {
		LOGGER.info("<------create user API started ------>");
		ResponseModel responseModel = ResponseModel.getInstance();
		user =  userService.login(user);
		responseModel.setObject(user);
		responseModel.setMessage(environment.getProperty(AssignmentConstant.USER_FETCHED_SUCCESS));
		return new ResponseEntity<>(responseModel, HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 201, message = "Successful created", response = ResponseModel.class) })
	@ApiOperation(value = "Register user.", notes = "This API used to logged out")
	public ResponseEntity<ResponseModel> logout(@Context HttpServletRequest request) throws AssignmentException {
		LOGGER.info("<------create user API started ------>");
		ResponseModel responseModel = ResponseModel.getInstance();
		userService.logout(request);
		responseModel.setMessage(environment.getProperty(AssignmentConstant.USER_CREATED_SUCCESS));
		return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
	}

}

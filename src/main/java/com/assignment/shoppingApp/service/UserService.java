package com.assignment.shoppingApp.service;



import javax.servlet.http.HttpServletRequest;
import com.assignment.shoppingApp.exception.AssignmentException;
import com.assignment.shoppingApp.model.UserModel;

public interface UserService {
	
	public UserModel createUser(UserModel user) throws AssignmentException;
    public UserModel login(UserModel user) throws AssignmentException;
    public void logout(HttpServletRequest request) throws AssignmentException;
    public UserModel getUserByAuthToken(String authToken) throws AssignmentException;
}

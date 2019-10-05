package com.assignment.shoppingApp.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.assignment.shoppingApp.model.UserModel;
import com.assignment.shoppingApp.service.UserService;


@Component
public class CustomInterceptor implements HandlerInterceptor{

	@Autowired
	private UserService userService; 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		String pathInfo = request.getServletPath();
		if((pathInfo.contains("/favourit")) || pathInfo.contains("/logout"))
		{
			String authToken = request.getHeader("authtoken");	
			UserModel user = userService.getUserByAuthToken(authToken);
			request.setAttribute("user", user);
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}

package com.assignment.shoppingApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorAppConfig implements WebMvcConfigurer{

	@Autowired
	private CustomInterceptor customInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(customInterceptor);
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	

}

package com.assignment.shoppingApp.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserModel implements Serializable{

	private static final long serialVersionUID = -2555283627419899952L;
	
	private Integer id;
	private String email;
	private String password;
	private String authtoken;
	private String name;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthtoken() {
		return authtoken;
	}
	public void setAuthtoken(String authtoken) {
		this.authtoken = authtoken;
	}
	
}

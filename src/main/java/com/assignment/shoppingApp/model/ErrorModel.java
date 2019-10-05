package com.assignment.shoppingApp.model;

import java.io.Serializable;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ErrorModel implements Serializable {
	private static final long serialVersionUID = -271581896020647350L;
	private Response.Status status;
	private String message;

	public ErrorModel() {
		super();
	}

	public ErrorModel(String message) {
		super();
		this.message = message;
	}

	public ErrorModel(String message, Status status) {
		super();
		this.status = status;
		this.message = message;
	}

	public Response.Status getStatus() {
		return status;
	}

	public void setStatus(Response.Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

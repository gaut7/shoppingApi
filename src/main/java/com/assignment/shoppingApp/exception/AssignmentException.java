package com.assignment.shoppingApp.exception;

import org.springframework.http.HttpStatus;

public class AssignmentException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AssignmentException() {
		super();
	}

	public AssignmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AssignmentException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssignmentException(String message) {
		super(message);
	}
	
	public AssignmentException(String message, HttpStatus httpStatus) {
		super(message);
	}


	public AssignmentException(Throwable cause) {
		super(cause);
	}
}

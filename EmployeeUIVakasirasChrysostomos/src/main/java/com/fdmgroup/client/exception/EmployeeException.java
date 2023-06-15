package com.fdmgroup.client.exception;

public class EmployeeException extends RuntimeException {

	private static final long serialVersionUID = -5840916949142927227L;
	private final String statusCode;
	
	public EmployeeException(String message, String statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public String getStatusCode() {
		return statusCode;
	}
}

package com.fdmgroup.api.exception;


/**
 * <h1>  Extending the Runtime exceptions</h1>
 *  Communicates with the ControllerAdvice 
 *  @see EmployeeControllerAdvice
 */
public class ResourceNotFoundException extends RuntimeException {
	

	private static final long serialVersionUID = -7025887842249512069L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}

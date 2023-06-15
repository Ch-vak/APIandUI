package com.fdmgroup.api.exception;

/**
 * <h1>  Extending the Runtime exceptions</h1>
 * Communicates with the ControllerAdvice 
 * @see EmployeeControllerAdvice
 */
public class EmployeeAlreadyExistException extends RuntimeException {


	private static final long serialVersionUID = -6202544535557148307L;

	public EmployeeAlreadyExistException(String message) {
		super(message);
	}
	
}

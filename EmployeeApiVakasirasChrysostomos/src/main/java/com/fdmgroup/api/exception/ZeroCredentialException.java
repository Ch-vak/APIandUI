package com.fdmgroup.api.exception;


/**
 * <h1>  Extending the Runtime exceptions</h1>
 *  Communicates with the ControllerAdvice 
 *  @see EmployeeControllerAdvice
 */
public class ZeroCredentialException extends RuntimeException {


	private static final long serialVersionUID = 6828299063125289725L;

	public ZeroCredentialException(String message) {
		super(message);
	}
}

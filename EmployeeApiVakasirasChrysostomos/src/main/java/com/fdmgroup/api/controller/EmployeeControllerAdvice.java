package com.fdmgroup.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fdmgroup.api.exception.EmployeeAlreadyExistException;
import com.fdmgroup.api.exception.ResourceNotFoundException;
import com.fdmgroup.api.exception.ZeroCredentialException;
/***
 * <h1> > Communication with  EmployeeService  </h1>
 * @see   <p>EmployeeService for checks and methods that throw the below exceptions</p>
 * @param ResourceNotFoundException
 * @param EmployeeAlreadyExistException
 * @param MethodArgumentNotValidException
 * @return String
 */
@ControllerAdvice
public class EmployeeControllerAdvice {


	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(value = EmployeeAlreadyExistException.class)
	public ResponseEntity<String> handleEmployeeAlreadyExistException(EmployeeAlreadyExistException ex){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException (MethodArgumentNotValidException ex) {
		List<ObjectError> errors = ex.getAllErrors();
		StringBuilder stringBuilder = new StringBuilder();
		errors.forEach(error -> stringBuilder.append(error.getDefaultMessage() + ","));
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(stringBuilder.toString());
	}
	
	@ExceptionHandler(value = ZeroCredentialException.class)
	public ResponseEntity<String> handleZeroCredentialException(ZeroCredentialException  ex){
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ex.getMessage());
	}
}

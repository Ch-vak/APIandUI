package com.fdmgroup.client.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fdmgroup.client.exception.EmployeeException;
import com.fdmgroup.client.exception.InvalidEditEmployeeException;
import com.fdmgroup.client.exception.InvalidEmployeeException;

@ControllerAdvice
public class EmployeeControllerExceptionAdvice {

	@ExceptionHandler(EmployeeException.class)
	public String handleEmployeeException(EmployeeException ex, Model model) {
		model.addAttribute("errorCode",ex.getStatusCode());
		model.addAttribute("errorMessage",ex.getMessage());
		return "error-page";
	}
	
	@ExceptionHandler(InvalidEmployeeException.class)
	public String handleInvalidEmployeeException(InvalidEmployeeException ex, Model model) {
		model.addAttribute("validationErrors", ex.getErrorMessages());
		return "forward:/addEmployee"; 
	}
	
	@ExceptionHandler(InvalidEditEmployeeException.class)
	public String handleInvalidEditEmployeeException(InvalidEditEmployeeException ex, Model model) {
		model.addAttribute("validationErrors", ex.getErrorMessages());
		return "forward:/editEmployee";
	}
}

package com.fdmgroup.client.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

	    @Override
	    public Exception decode(String methodKey, Response response) {
	        switch (response.status()){
	            case 400:       
	            	if(methodKey.contains("update")) {
            			return new InvalidEditEmployeeException(methodKey + " " +response.toString() );
	            	}else {
	                return new InvalidEmployeeException(methodKey + " " +response.toString());}
	            case 404:
	                return new EmployeeException("Employee not found -> of the java method that invoked the request:" +methodKey ,response.toString());
	            case 405:
	            	return new EmployeeException("Method not allowed:" +methodKey, response.toString());
	            case 409:
	                return new EmployeeException("Employee	is present ->of the java method that invoked the request:" +methodKey,response.toString());
	            default:	            	
	                return new EmployeeException( methodKey, response.toString());
	        }
	    }
}


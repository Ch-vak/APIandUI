package com.fdmgroup.client.exception;

import java.util.Arrays;
import java.util.List;

public class InvalidEmployeeException extends RuntimeException {

	private static final long serialVersionUID = -8903671251497339358L;

    private final List<String> errorMessages;

    public InvalidEmployeeException(String csvString) {
        super("invalid employee");
        this.errorMessages = Arrays.asList(csvString.split(","));
    }
    public List<String> getErrorMessages(){
        return this.errorMessages;
    }
}

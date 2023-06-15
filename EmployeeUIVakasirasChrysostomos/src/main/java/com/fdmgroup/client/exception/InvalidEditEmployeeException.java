package com.fdmgroup.client.exception;

import java.util.Arrays;
import java.util.List;

public class InvalidEditEmployeeException extends RuntimeException {

	private static final long serialVersionUID = 9058710732974532851L;

    private final List<String> errorMessages;

    public InvalidEditEmployeeException(String csvString) {
        super("invalid employee while updating");
        this.errorMessages = Arrays.asList(csvString.split(","));
    }
    public List<String> getErrorMessages(){
        return this.errorMessages;
    }
}

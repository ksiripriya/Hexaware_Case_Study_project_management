package com.hexaware.exception;
//Custom exception class 
public class EmployeeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(String message) {
        super(message);// Calls the constructor of the parent Exception class.
    }
}

package com.hexaware.exception;
//Custom exception class
	public class ProjectNotFoundException extends Exception {
	    private static final long serialVersionUID = 1L;

	    public ProjectNotFoundException(String message) {
	        super(message);//// Calls the parent Exception class constructor to store the message.
	    }

	    }
	

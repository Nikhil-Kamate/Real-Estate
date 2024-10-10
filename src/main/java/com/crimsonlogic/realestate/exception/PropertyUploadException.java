package com.crimsonlogic.realestate.exception;

public class PropertyUploadException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141209006316162447L;
	
	 public PropertyUploadException(String message) {
	        super(message);
	    }

	    public PropertyUploadException(String message, Throwable cause) {
	        super(message, cause);
	    }

}

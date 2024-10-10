package com.crimsonlogic.realestate.exception;

public class UserRegistrationException extends Exception{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -831752193365057762L;

	public UserRegistrationException(String message) {
	        super(message);
	    }

	    public UserRegistrationException(String message, Throwable cause) {
	        super(message, cause);
	    }
}

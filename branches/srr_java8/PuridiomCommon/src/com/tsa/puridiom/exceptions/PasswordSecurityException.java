package com.tsa.puridiom.exceptions;


/**
 * Created on October 20, 2004
 * @author Kelli
 * com.tsa.puridiom.exceptions.PasswordException.java
 * 
 */
public class PasswordSecurityException extends Exception {

	public PasswordSecurityException() {
		super();
	}
	
	public PasswordSecurityException(String message) {
		super(message);
	}
	
	public PasswordSecurityException(String message, Exception e) {
		e.printStackTrace();
	}

	public PasswordSecurityException(Throwable cause) {
		super(cause);
	}

	public PasswordSecurityException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public void printStackTrace() {
	    // This is not a fatal exception no need to print anything
	}
}
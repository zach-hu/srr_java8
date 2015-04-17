package com.tsa.puridiom.exceptions;


/**
 * Created on October 20, 2004
 * @author Kelli
 * com.tsa.puridiom.exceptions.PasswordException.java
 * 
 */
public class PasswordException extends Exception {

	public PasswordException() {
		super();
	}
	
	public PasswordException(String message) {
		super(message);
	}
	
	public PasswordException(String message, Exception e) {
		e.printStackTrace();
	}

	public PasswordException(Throwable cause) {
		super(cause);
	}

	public PasswordException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public void printStackTrace() {
	    // This is not a fatal exception no need to print anything
	}
}
package com.tsa.puridiom.exceptions;


/**
 * Created on October 02, 2007
 * @author Kelli
 * com.tsa.puridiom.exceptions.InvalidEmailAddressException.java
 *
 */
public class InvalidEmailAddressException extends Exception {

	public InvalidEmailAddressException() {
		super();
	}

	public InvalidEmailAddressException(String message) {
		super(message);
	}

	public InvalidEmailAddressException(String message, Exception e) {
		e.printStackTrace();
	}

	public InvalidEmailAddressException(Throwable cause) {
		super(cause);
	}

	public InvalidEmailAddressException(String message, Throwable cause) {
		super(message, cause);
	}

	public void printStackTrace() {
	    // This is not a fatal exception no need to print anything
	}
}
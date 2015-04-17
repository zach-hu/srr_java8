package com.tsagate.foundation.exceptions;


/**
 * Created on March 15, 2005
 * @author Kelli
 * project: Foundation
 * com.tsagate.foundation.exceptions.DatabaseConnectionException.java
 * 
 */
public class DatabaseConnectionException extends Exception {

	public DatabaseConnectionException() {
		super();
	}
	
	public DatabaseConnectionException(String message) {
		super(message);
	}
	
	public DatabaseConnectionException(String message, Exception e) {
		e.printStackTrace();
	}

	public DatabaseConnectionException(Throwable cause) {
		super(cause);
	}

	public DatabaseConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
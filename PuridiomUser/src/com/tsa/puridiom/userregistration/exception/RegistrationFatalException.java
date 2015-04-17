package com.tsa.puridiom.userregistration.exception;

import com.tsagate.foundation.utility.*;
/**
 * Created on April 2, 2009
 * @author Kelli
 * project: ExpressUser
 * com.tsa.puridiom.userregistration.exception.RegistrationException.java
 *
 */
public class RegistrationFatalException extends Exception
{
	/**
	 *
	 */
	public RegistrationFatalException()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 */
	public RegistrationFatalException(String message)
	{
		super(message);
		Log.error(this, message);
	}

	/**
	 * @param message
	 * @param exception
	 */
	public RegistrationFatalException(String message, Exception e)
	{
		e.printStackTrace();
		Log.error(this, message);
	}
	/**
	 * @param cause
	 */
	public RegistrationFatalException(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 * @param cause
	 */
	public RegistrationFatalException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}

package com.tsa.puridiom.userregistration.exception;

import com.tsagate.foundation.utility.*;
/**
 * Created on April 2, 2009
 * @author Kelli
 * project: ExpressUser
 * com.tsa.puridiom.userregistration.exception.RegistrationException.java
 *
 */
public class RegistrationException extends Exception
{
	/**
	 *
	 */
	public RegistrationException()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 */
	public RegistrationException(String message)
	{
		super(message);
		Log.error(this, message);
	}

	/**
	 * @param message
	 * @param exception
	 */
	public RegistrationException(String message, Exception e)
	{
		e.printStackTrace();
		Log.error(this, message);
	}
	/**
	 * @param cause
	 */
	public RegistrationException(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 * @param cause
	 */
	public RegistrationException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}

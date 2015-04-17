package com.tsa.puridiom.requisition.exception;

import com.tsagate.foundation.utility.*;
/**
 * Created on Jan 8, 2006
 * @author Kelli
 * project: HiltonRequisitions
 * com.tsa.puridiom.requisition.exception.RequisitionException.java
 * 
 */
public class RequisitionException extends Exception
{
	/**
	 * 
	 */
	public RequisitionException()
	{
		super();
	}
	/**
	 * @param message
	 */
	public RequisitionException(String message)
	{
		super(message);
		Log.error(this, message);
	}
	
	/**
	 * @param message
	 * @param exception
	 */
	public RequisitionException(String message, Exception e)
	{
		e.printStackTrace();
		Log.error(this, message);
	}
	/**
	 * @param cause
	 */
	public RequisitionException(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 * @param cause
	 */
	public RequisitionException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}

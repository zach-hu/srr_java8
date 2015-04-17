package com.tsa.puridiom.receipt.exception;

import com.tsagate.foundation.utility.*;
/**
 * Created on Jan 8, 2006
 * @author Kelli
 * project: HiltonRequisitions
 * com.tsa.puridiom.requisition.exception.RequisitionException.java
 * 
 */
public class ReceiptRejectedItemException extends Exception
{
	/**
	 * 
	 */
	public ReceiptRejectedItemException()
	{
		super();
	}
	/**
	 * @param message
	 */
	public ReceiptRejectedItemException(String message)
	{
		super(message);
		Log.error(this, message);
	}
	
	/**
	 * @param message
	 * @param exception
	 */
	public ReceiptRejectedItemException(String message, Exception e)
	{
		e.printStackTrace();
		Log.error(this, message);
	}
	/**
	 * @param cause
	 */
	public ReceiptRejectedItemException(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 * @param cause
	 */
	public ReceiptRejectedItemException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}

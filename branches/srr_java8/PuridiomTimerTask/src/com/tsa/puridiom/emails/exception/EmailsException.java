package com.tsa.puridiom.emails.exception;

import com.tsagate.foundation.utility.Log;

/**
 * This class provides a single exception type that can be thrown
 * within the HiltonServices module.
 */
public class EmailsException extends Exception
{

  /**
   * Creates an UNDEFINED_EXCEPTION_CODE with the specified exception message
   */
  public EmailsException(String pExceptionMessage)
  {
	  super(pExceptionMessage);
	    Log.error(this, pExceptionMessage);
  }

  /**
   * Wraps the throwable within the exception
   */
  public EmailsException(Throwable pThrowable)
  {
    super(pThrowable.getMessage());
  }


  /**
   * Returns the exception message
   */
  public String getMessage()
  {
    StringBuffer stringBuffer = new StringBuffer(100);

    stringBuffer.append("Message: ");

   stringBuffer.append(super.getMessage());

    return stringBuffer.toString();
  }
}

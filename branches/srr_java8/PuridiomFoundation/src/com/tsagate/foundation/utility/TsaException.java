/*
 * Created on Jan 27, 2004
 * @author renzo
 *
 */
package com.tsagate.foundation.utility;

/**
 *
 * Created on Jan 27, 2004
 * @author renzo
 * project: Foundation
 * com.tsagate.foundation.utility.TsaException.java
 *
 */
public class TsaException extends Exception
{
    /**
     *
     */
    public TsaException()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @param message
     */
    public TsaException(String message)
    {
        super(message);
        Log.error(this, message);
    }

    /**
     * @param message
     * @param exception
     */
    public TsaException(String message, Exception e)
    {
        super(message);
        Log.error(this, message + "Exception[" + e.getMessage() + "]");
        e.printStackTrace();
    }
    /**
     * @param cause
     */
    public TsaException(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }
    /**
     * @param message
     * @param cause
     */
    public TsaException(String message, Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
}

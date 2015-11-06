/*
 * Created on Dec 8, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.exceptions;

import javax.servlet.ServletException;

import com.tsagate.foundation.utility.Log;

/**
 * @author 
 *
 */
public class InvalidUserSessionException extends ServletException
{
    
    public InvalidUserSessionException(String message)
	{
        super(message);
		Log.error(this, message);
	}

}

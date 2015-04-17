/*
 * Created on Dec 8, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs.exceptions;

import com.tsagate.foundation.utility.Log;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProcessException extends Exception
{
    private static StringBuffer msg = new StringBuffer("Error ocurred processing: ") ;
    
    
    public ProcessException(String message)
	{
        super(ProcessException.msg.append(message).toString());
		Log.error(this, ProcessException.msg.toString());
	}

}

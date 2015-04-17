/*
 * Created on Oct 28, 2005
 */
package com.tsa.puridiom.po.exceptions;

/**
 * @author Kelli
 */
public class PoCancelException extends Exception {
    
    public PoCancelException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PoCancelException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public PoCancelException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public PoCancelException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
    
    public void printStackTrace() {
        // Do Nothing - his is not a fatal exception so there is no need to write the error to the log file
    }
}

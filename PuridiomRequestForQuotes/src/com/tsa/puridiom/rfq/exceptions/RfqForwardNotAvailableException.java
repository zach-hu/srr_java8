/*
 * Created on Oct 28, 2005
 */
package com.tsa.puridiom.rfq.exceptions;

/**
 * @author Kelli
 */
public class RfqForwardNotAvailableException extends Exception {
    
    public RfqForwardNotAvailableException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public RfqForwardNotAvailableException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public RfqForwardNotAvailableException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public RfqForwardNotAvailableException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
    
    public void printStackTrace() {
        // Do Nothing - his is not a fatal exception so there is no need to write the error to the log file
    }
}

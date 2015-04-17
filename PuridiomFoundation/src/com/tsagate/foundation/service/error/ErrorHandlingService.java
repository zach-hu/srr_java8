/*
 * Created on Aug 12, 2003 
 */
package com.tsagate.foundation.service.error;

import java.util.logging.*;

/**
 * @author Administrator 
 */
public class ErrorHandlingService {
	
	private static final ErrorHandlingService SINGLETON = new ErrorHandlingService();
	private Logger logger = null;
	
	private ErrorHandlingService(){
		try{
			PuridiomLogManager plm = PuridiomLogManager.getInstance();
			logger = plm.getLogger();
		}
		catch(Exception exception){
			System.err.println("Error in creating log file: " + exception.toString());
		}
	}
	public static ErrorHandlingService getInstance(){
		return SINGLETON;
	}
	
	public void handleError(String className, String methodName, Object [] paramaters, Error error){
		//
		logger.log(Level.WARNING, error.toString());
	}
	public void handleException(String className, String methodName, Object [] paramaters, Exception exception){
		logger.log(Level.WARNING, "Exception occured in: [" + className + "." + methodName + "] " + exception.toString());
	}	
}

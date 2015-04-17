/*
 * Created on Aug 16, 2003 
 */
package com.tsagate.foundation.service.error;


import java.util.*;
import java.util.logging.*;
import java.text.*;


/**
 * @author Administrator 
 */
public class PuridiomLogManager {
	private static String fileSeparator = System.getProperty("file.separator");
	private static String userDirectory = System.getProperty("user.dir");
	private Logger logger;
	public String logLevel = "";
	public int numberOfLogs = 0;
	private static PuridiomLogManager SINGLETON = new PuridiomLogManager();
	
	private PuridiomLogManager(){
		setUpLogFile();
	}
	public static PuridiomLogManager getInstance(){
		if(SINGLETON == null){
			SINGLETON = new PuridiomLogManager();
		}
		return SINGLETON;
	}
	private void setUpLogFile(){
		try {
			FileHandler fh = new FileHandler(createDailyUniqueLogName());
			logger = Logger.getLogger("com.tsagate.puridiom");
			logger.addHandler(fh);
			logger.setLevel(Level.ALL);
			logger.log(Level.INFO, "PuridiomLogManager.setUpLogFile() - log set up complete");
			LogManager manager = LogManager.getLogManager();
			manager.addLogger(logger);
		}
		catch (Exception exception) {
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("PuridiomLogManager", "setUpLogFile()", null, exception);
		}
	}
	
	private static String createDailyUniqueLogName(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");	
		StringBuffer logName = new StringBuffer();
		//logName.append(fileSeparator);
		logName.append(userDirectory);
		logName.append(fileSeparator);	
		logName.append(formatter.format(new Date()));
		logName.append("-LOG.xml");	
		//system.out.println(logName.toString());
		return logName.toString();
	}	
	private void loadProperties(){	
	}
	public Logger getLogger(){
		try{
			LogManager logManager = LogManager.getLogManager();
			logger = logManager.getLogger("com.tsagate.puridiom");			
		}
		catch(Exception exception){
			ErrorHandlingService ehs = ErrorHandlingService.getInstance();
			ehs.handleException("PuridiomLogManager", "getLogger()", null, exception);
		}
		finally{
			return logger;
		}		
	}
}

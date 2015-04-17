/*
 * Created on Nov 21, 2003
 */
package com.tsagate.foundation.utility;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 * project: test
 * com.tsagate.commonLog4jTest.java
 */
public class Log
{
	static Logger logger = null;
	

	public static void logger(Object o)
	{
		Class c =o.getClass();
		Log.logger = Logger.getLogger(c);
		//ResourceBundle resources = ResourceBundle.getBundle("com.properties.tsagate"); 
		//String filename = resources.getString("properties-path") + "log4j.properties";
		String filename = DictionaryManager.getInstance("host", "").getProperty("logproperties-location", "log4j");
		PropertyConfigurator.configure(filename);
	}
	
	public static void warn(Object o, String msg)
	{
		Log.logger(o);
		Log.logger.warn(msg);
	}
	
	public static void error(Object o, String msg)
	{
	    //system.out.println(msg);
		Log.logger(o);
		Log.logger.error(msg);
	}
	
	public static void error(Object o, Exception e)
	{
		Log.logger(o);
		Log.logger.error(e);
	}
	
	public static void debug(Object o, String msg)
	{
	    //system.out.println(msg);
		Log.logger(o);
		Log.logger.debug(msg);
	}
	public static void info(Object o, String msg)
	{
	    //system.out.println(msg);
		Log.logger(o);
		Log.logger.info(msg);
	}
	public static void main(String[] args)
	{
		Log.warn("hello", "hello from TSACommon");
	}
}

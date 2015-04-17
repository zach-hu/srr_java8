/*
 * Created on March 11, 2004 
 */
package com.tsa.puridiom.supplierportal.handlers;

import java.util.*;

/**
 * @author Kelli
 */
public class HandlerFactory {
	private static final HandlerFactory singleton = new HandlerFactory();
	public static HandlerFactory getInstance() {
		return singleton;
	}	
	public IHandler getHandler(Map requestParameters) throws Exception {
		IHandler handler = null;
		String handlerName = this.getPackageName();
		String nameFromRequestMap = (String)requestParameters.get("handler");
		handlerName += nameFromRequestMap;
		try{
			if((nameFromRequestMap == null) || (nameFromRequestMap.equals(""))){
				handler = new DoNothingHandler();
			}
			else{
				handler = (IHandler)Class.forName(handlerName).newInstance();
			}		
		}
		catch(Exception exception){
			throw exception;
		}
		finally{
			return handler;
		}
	}
	private String getPackageName() throws java.lang.Exception {
		String packageName = "";
		try {
			Class c = this.getClass();
			if (c != null) {
				packageName = c.getName().substring(0, c.getName().lastIndexOf(".") + 1);
			} else {
				packageName = "com.tsagate.puridiom.bidboard.handlers.";
			}
		} 
		catch (Exception e) {
			throw e;
		} 
		finally {
			if (packageName.indexOf("com", 0) < 0) {
				packageName = "com.tsagate.puridiom.bidboard.handlers.";
			}
			return packageName;
		}
	}
}

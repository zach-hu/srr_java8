/*
 * Created on Aug 19, 2003 
 */
package com.tsagate.foundation.processengine;


import java.util.*;

/**
 * @author Administrator 
 */
public class SampleTask extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Object returnObject = null;
		try {
			//system.out.println("executing: " + this.toString());
			//System.gc();
			////system.out.println(Runtime.getRuntime().maxMemory() + "/" + Runtime.getRuntime().totalMemory()+ "/" + Runtime.getRuntime().freeMemory());
			
			Map incomingRequest = (Map)object;	
			returnObject = incomingRequest;		

			this.setStatus(Status.SUCCEEDED);
			////system.out.println(lineItem.toString());
			//returnObject = lineItem			
		}
		catch (Exception e) {
			throw e;
		}
		finally{
			return returnObject;
		}
	}
}

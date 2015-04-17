/*
 * Created on June 07, 2004
 */
package com.tsa.puridiom.disbline.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class DisbLineAllocateAmountSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		try {
			String icHeader = (String)incomingRequest.get("Account_icHeader") ;
			String icLine = (String)incomingRequest.get("Account_icLine") ;
			incomingRequest.put("DisbHeader_icDsbHeader",icHeader) ;
	        incomingRequest.put("DisbHeader_icDsbLine",icLine) ;
	        incomingRequest.put("DisbLine_icDsbLine",icLine) ;
	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
        
		return null  ;
	}
	
}

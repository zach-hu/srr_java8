/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class RequisitionHeaderAllocateAmountSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		try {
			String icHeader = (String)incomingRequest.get("Account_icHeader") ;
			String icLine = (String)incomingRequest.get("Account_icLine") ;
			incomingRequest.put("RequisitionHeader_icReqHeader",icHeader) ;
	        incomingRequest.put("RequisitionHeader_icReqLine",icLine) ;
	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
        
		return null  ;
	}
	
}

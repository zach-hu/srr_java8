/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
/**
 * @author Administrator 
 */
public class RequisitionHeaderUpdateSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED) ;

		incomingRequest.put("RequisitionLine_icReqHeader",(String)incomingRequest.get("RequisitionHeader_icReqHeader")) ;
		incomingRequest.put("Account_icHeader",(String)incomingRequest.get("RequisitionHeader_icReqHeader"));
		incomingRequest.put("Account_icLine", "0");
		incomingRequest.put("Default_referenceType", "RQH");
		
		return null ;
	}
}

/*
 * Created on Dec 2, 2003
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;
/**
 * @author Kelli
 */
public class RequisitionHeaderSetSelectedVendor extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		
		try {
			String	vendorCode = (String) incomingRequest.get("vendorCode");
			String	vendContactCode = (String) incomingRequest.get("vendContactCode");
			
			incomingRequest.put("RequisitionHeader_vendorCode", vendorCode);
			incomingRequest.put("RequisitionHeader_vendContactCode", vendContactCode);
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
        
		return null  ;
	}
}

package com.tsa.puridiom.requisition.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class RequisitionSaveasSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			//	this is the Requisition number that was generated or passed in through the request
			String	requisitionNumber = (String) incomingRequest.get("RequisitionHeader_requisitionNumber");
			
			incomingRequest.put("newRequisitionHeader_requisitionNumber", requisitionNumber) ;
			incomingRequest.put("newRequisitionLine_requisitionNumber", requisitionNumber) ;
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}

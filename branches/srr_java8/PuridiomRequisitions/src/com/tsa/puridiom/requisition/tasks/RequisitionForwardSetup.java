package com.tsa.puridiom.requisition.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionForwardSetup extends Task	{
	
	public Object  executeTask (Object object) throws Exception
	{
		
		Map incomingRequest = (Map)object;
		try {
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			String	icReqHeaderString = String.valueOf(requisitionHeader.getIcReqHeader());

			incomingRequest.put("RequisitionLine_icReqHeader", icReqHeaderString);
			incomingRequest.put("newStatus",requisitionHeader.getStatus()) ;
			incomingRequest.put("budStatus",requisitionHeader.getStatus()) ;
			incomingRequest.put("splitCount","1") ;
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}		
		
		return null ;
	}
}

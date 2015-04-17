package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RequisitionRoutingDisplaySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
		
		incomingRequest.put("ApprovalLog_icHeader", icReqHeader);
		
		if (!incomingRequest.containsKey("RequisitionHeader_status")) {
		    RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		    if (requisitionHeader != null) {
		        incomingRequest.put("RequisitionHeader_status", requisitionHeader.getStatus());
		        incomingRequest.put("formCurrencyCode", requisitionHeader.getCurrencyCode());
		    }
		}
		
		return null ;
	}
}

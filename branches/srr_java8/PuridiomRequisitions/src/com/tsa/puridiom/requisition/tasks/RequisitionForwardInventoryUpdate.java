package com.tsa.puridiom.requisition.tasks;
import java.util.Map;

import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.common.documents.DocumentStatus;

public class RequisitionForwardInventoryUpdate extends Task	{
	
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
//		incomingRequest.put("newStatus",DocumentStatus.REQ_APPROVING) ;
		// Still to do
		 
		return null ;
	}
}

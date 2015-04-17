package com.tsa.puridiom.requisition.tasks;
import java.util.Map;

import com.tsagate.foundation.processengine.Task;

public class ReqRoutingDisplaySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");

		incomingRequest.put("ApprovalLog_icHeader", icReqHeader);

		return null ;
	}
}

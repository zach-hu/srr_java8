package com.tsa.puridiom.po.approvals.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class PoRoutingDisplaySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String icPoHeader = (String) incomingRequest.get("PoHeader_icPoHeader");

		incomingRequest.put("ApprovalLog_icHeader", icPoHeader);

		if (!incomingRequest.containsKey("PoHeader_status")) {
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
		    if (poHeader != null) {
		        incomingRequest.put("PoHeader_status", poHeader.getStatus());
		        incomingRequest.put("formCurrencyCode", poHeader.getCurrencyCode());
		    }
		}

		return null ;
	}
}

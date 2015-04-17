package com.tsa.puridiom.rfq.tasks;
import java.util.Map;

import com.tsagate.foundation.processengine.Task;

public class RfqRoutingDisplaySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String icRfqHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader");

		incomingRequest.put("ApprovalLog_icHeader", icRfqHeader);

		return null ;
	}
}

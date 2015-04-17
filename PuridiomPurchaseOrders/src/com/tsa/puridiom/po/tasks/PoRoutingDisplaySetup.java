package com.tsa.puridiom.po.tasks;
import java.util.Map;

import com.tsagate.foundation.processengine.Task;

public class PoRoutingDisplaySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String icReqHeader = (String) incomingRequest.get("PoHeader_icPoHeader");

		incomingRequest.put("ApprovalLog_icHeader", icReqHeader);

		return null ;
	}
}

package com.tsa.puridiom.invoice.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Task;

public class InvoiceSendToCostpointSetBatch extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		incomingRequest.put("batch", Boolean.TRUE);
		
		return null;
	}
}

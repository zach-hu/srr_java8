package com.tsa.puridiom.billto.tasks;
import java.util.Map;

import com.tsagate.foundation.processengine.Task;

public class BillToSetup extends Task
{
	public void executeTask(Map incomingRequest) throws Exception
	{
		incomingRequest.put("BillTo.icHeader", "0");
		incomingRequest.put("BillTo.icLine", "0");
		incomingRequest.put("BillTo.billToCode", "");
		incomingRequest.put("BillTo.quantity", "0");
		incomingRequest.put("BillTo.attention", "");
	}
}
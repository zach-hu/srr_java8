package com.tsa.puridiom.shipto.tasks;
import java.util.Map;

import com.tsagate.foundation.processengine.Task;

public class ShipToSetup extends Task
{
	public void executeTask(Map incomingRequest) throws Exception
	{
		incomingRequest.put("ShipTo.icHeader", "0");
		incomingRequest.put("ShipTo.icLine", "0");
		incomingRequest.put("ShipTo.shipToCode", "");
		incomingRequest.put("ShipTo.quantity", "0");
		incomingRequest.put("ShipTo.attention", "");
		incomingRequest.put("ShipTo.shipDate", "2003-10-31");
		incomingRequest.put("ShipTo.shipToPriority", "");
		incomingRequest.put("ShipTo.codeType", "");
	}
}
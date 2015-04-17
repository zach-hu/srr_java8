package com.tsa.puridiom.requisitionline.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RequisitionLineInvItemLookup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

//		incomingRequest.put("InvLocation_itemLocation",(String) incomingRequest.get("RequisitionLine_itemLocation"));
		incomingRequest.put("InvItem_itemNumber",(String) incomingRequest.get("RequisitionLine_itemNumber"));
			
		this.status = Status.SUCCEEDED;
		return result;
	}
}
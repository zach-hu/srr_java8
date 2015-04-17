/**
 * 
 * Created on Jan 21, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineInvItemLookup.java
 * 
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;


public class PoLineInvItemLookup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		incomingRequest.put("InvItem_itemNumber", incomingRequest.get("PoLine_itemNumber"));
		this.setStatus(Status.SUCCEEDED);
		return null;
	}

}

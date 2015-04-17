/**
 * 
 * Created on Feb 4, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.GetBidEntities.java
 * 
 */
package com.tsa.puridiom.poline.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class GetBidEntities extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List bidLines = (ArrayList)incomingRequest.get("lines");
		List rfqLines = new ArrayList();
		//List text = new ArrayList();
		List vendors = new ArrayList();
		List bids = new ArrayList();
		
		for (Iterator iter = bidLines.iterator(); iter.hasNext();)
		{
			Object o[] = (Object[])iter.next();
			rfqLines.add(o[0]);
			//text.add(o[1]);
			vendors.add(o[1]);
			bids.add(o[2]);
		}
		incomingRequest.put("rfqLines", rfqLines);
		//incomingRequest.put("doctexts", text);
		incomingRequest.put("rfqVendors", vendors);
		incomingRequest.put("rfqBids", bids);
		
		this.setStatus(Status.SUCCEEDED);
		return null;
	}

}

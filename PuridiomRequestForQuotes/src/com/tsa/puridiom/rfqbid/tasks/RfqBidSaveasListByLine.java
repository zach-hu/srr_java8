package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.RfqBid;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class RfqBidSaveasListByLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain rfqBidList */
			List	rfqBidList = (List) incomingRequest.get("rfqBidList");
			
			for (int i=0; i < rfqBidList.size(); i++)
			{
				incomingRequest.put("rfqBid", (RfqBid) rfqBidList.get(i));
	
				RfqBidSaveasByLine saveasTask = new RfqBidSaveasByLine();
				RfqBid	rfqBid = (RfqBid) saveasTask.executeTask(incomingRequest);
				
				if (saveasTask.getStatus() != Status.SUCCEEDED)
				{
					throw new Exception("RfqBidSaveas failed.");
				}
				
				rfqBidList.set(i, rfqBid);
			}
			
			result = rfqBidList;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
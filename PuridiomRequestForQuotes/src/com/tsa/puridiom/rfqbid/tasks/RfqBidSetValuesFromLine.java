package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

public class RfqBidSetValuesFromLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");
			List rfqBidList = (List) incomingRequest.get("rfqBidList");
			
			if (rfqBidList != null)
			{
				for (int i=0; i < rfqBidList.size(); i++)
				{
					RfqBid rfqBid = (RfqBid) rfqBidList.get(i);
					if (rfqBid != null)
					{
						rfqBid.setQuantity(rfqLine.getQuantity());
						rfqBid.setUmCode(rfqLine.getUmCode());
						rfqBid.setUmFactor(rfqLine.getUmFactor());
						
						rfqBidList.set(i, rfqBid);
					}
				}
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
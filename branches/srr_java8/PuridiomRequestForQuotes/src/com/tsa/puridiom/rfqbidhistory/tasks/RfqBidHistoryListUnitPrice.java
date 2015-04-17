package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsa.puridiom.entity.RfqBidHistory;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class RfqBidHistoryListUnitPrice extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List rfqbidhistorylist = (List)incomingRequest.get("rfqBidHistoryList");
			List UnitPriceList = new ArrayList();
			 for(int i = 0; i < rfqbidhistorylist.size(); i++)
		        {
		        	RfqBidHistory bid = (RfqBidHistory)rfqbidhistorylist.get(i);
		        	UnitPriceList.add(bid.getUnitPrice());
		        }
			result = UnitPriceList;
			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}
		return result ;
	}
}
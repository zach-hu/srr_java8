package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RfqBidListSetCurrentCurrencyFactor extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			List rfqBidList = (List) incomingRequest.get("rfqBidList");
			
			if (rfqBidList != null)
			{
			    String organizationId = (String) incomingRequest.get("organizationId");
			    String userId = (String) incomingRequest.get("userId");
			    RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			    
				for (int i=0; i < rfqBidList.size(); i++)
				{
					RfqBid rfqBid = (RfqBid) rfqBidList.get(i);
					if (rfqBid != null)
					{
					    RfqBidGetCurrentCurrencyFactor task = new RfqBidGetCurrentCurrencyFactor();
					    Map requestParameters = new HashMap();
					    requestParameters.put("organizationId", organizationId);
					    requestParameters.put("userId", userId);
					    requestParameters.put("rfqBid", rfqBid);
					    requestParameters.put("rfqHeader", rfqHeader);
					    
					    String currencyFactorString = (String) task.executeTask(requestParameters);
					    rfqBid.setCurrencyFactor(new BigDecimal(currencyFactorString));
						
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
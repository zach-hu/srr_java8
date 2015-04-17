package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqBidAddForLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			List rfqVendorList = (List) incomingRequest.get("rfqVendorList");
			
			if (rfqVendorList != null && rfqVendorList.size() > 0)
			{
				List rfqBidList = new ArrayList();
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("rfqbid-add.xml");
								
				for (int i=0; i < rfqVendorList.size(); i++)
				{
					incomingRequest.put("rfqVendor", (RfqVendor) rfqVendorList.get(i));
					process.executeProcess(incomingRequest);
					
					if (process.getStatus() == Status.SUCCEEDED)
					{
						rfqBidList.add(incomingRequest.get("rfqBid"));
						
						incomingRequest.remove("rfqBid");
						incomingRequest.remove("rfqVendor");
					}
					else
					{
						this.setStatus(process.getStatus());
						throw new Exception("Error adding bids for suppliers.");
					}
				}
				
				result = rfqBidList;
			}
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
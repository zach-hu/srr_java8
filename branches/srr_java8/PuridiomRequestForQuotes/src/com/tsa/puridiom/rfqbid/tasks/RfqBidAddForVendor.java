package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqBidAddForVendor extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			List rfqLineList = (List) incomingRequest.get("rfqLineList");
			
			if (rfqLineList != null && rfqLineList.size() > 0)
			{
				List rfqBidList = new ArrayList();
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("rfqbid-add.xml");

				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("rfqVendor", incomingRequest.get("rfqVendor"));
				
				for (int i=0; i < rfqLineList.size(); i++)
				{
					updateParameters.put("rfqLine", (RfqLine) rfqLineList.get(i));
					process.executeProcess(updateParameters);
					
					if (process.getStatus() == Status.SUCCEEDED)
					{
						rfqBidList.add(updateParameters.get("rfqBid"));
						
						updateParameters.remove("rfqBid");
						updateParameters.remove("rfqLine");
					}
					else
					{
						this.setStatus(process.getStatus());
						throw new Exception("Error adding bids for suppliers.");
					}
				}
				
				this.setStatus(Status.SUCCEEDED);
				result = rfqBidList;
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
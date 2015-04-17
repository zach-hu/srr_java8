package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class RfqVendorSaveasList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain rfqVendorList */
			List	rfqVendorList = (List) incomingRequest.get("rfqVendorList");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("rfqvendor-saveas-by-id.xml");
			
			for (int i=0; i < rfqVendorList.size(); i++)
			{
				incomingRequest.put("rfqVendor", (RfqVendor) rfqVendorList.get(i));
				
				process.executeProcess(incomingRequest);
				
				RfqVendor rfqVendor = (RfqVendor) incomingRequest.get("rfqVendor");
				
				if (process.getStatus() != Status.SUCCEEDED)
				{
					throw new Exception("RfqVendorSaveasById process failed.");
				}

				rfqVendorList.set(i, rfqVendor);
			}

			result = rfqVendorList;
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
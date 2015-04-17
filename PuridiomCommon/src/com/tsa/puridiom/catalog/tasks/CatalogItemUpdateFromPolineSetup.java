package com.tsa.puridiom.catalog.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.*;

public class CatalogItemUpdateFromPolineSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String catalogId = (String) incomingRequest.get("PoLine_catalogId");
			String umCode = (String) incomingRequest.get("PoLine_umCode");
			String cost = (String) incomingRequest.get("PoLine_unitPrice");
			String itemNumber = (String) incomingRequest.get("PoLine_itemNumber");

		    incomingRequest.put("catalogId", catalogId);
	        incomingRequest.put("umCode", umCode);
	        incomingRequest.put("cost", cost);
	        incomingRequest.put("itemNumber", itemNumber);

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
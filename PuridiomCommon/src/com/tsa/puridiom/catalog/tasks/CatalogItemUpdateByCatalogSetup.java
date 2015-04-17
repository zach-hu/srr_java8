package com.tsa.puridiom.catalog.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class CatalogItemUpdateByCatalogSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("CatalogItem_catalogId", incomingRequest.get("Catalog_catalogId"));
			incomingRequest.put("CatalogItem_status", incomingRequest.get("Catalog_status"));
			incomingRequest.put("CatalogItem_dateExpires", incomingRequest.get("Catalog_dateExpires"));

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
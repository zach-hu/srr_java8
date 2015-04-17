package com.tsa.puridiom.requisitionline.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RequisitionLineCatalogItemLookup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		incomingRequest.put("Catalog_catalogId",(String) incomingRequest.get("RequisitionLine_catalogId"));
		incomingRequest.put("CatalogItem_catalogId",(String) incomingRequest.get("RequisitionLine_catalogId"));
		incomingRequest.put("CatalogItem_itemNumber",(String) incomingRequest.get("RequisitionLine_itemNumber"));
			
		this.status = Status.SUCCEEDED;
		return result;
	}
}
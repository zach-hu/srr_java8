package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.entity.CatalogItem;
import com.tsagate.foundation.processengine.*;

import java.util.List;
import java.util.Map;

public class CatalogRetrieveSetup extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Object result = null;
		
		try
		{
			Map incomingRequest = (Map) object;

			CatalogItem  catalogItem = null;
			
			List catalogItemList = (List) incomingRequest.get("catalogItemList");
			if (catalogItemList != null)
			{
				if (catalogItemList.size() > 0)
				{
					catalogItem = (CatalogItem) catalogItemList.get(0);
				}
			}
			else
			{
				catalogItem = (CatalogItem) incomingRequest.get("catalogItem");
			}
			
			if (catalogItem != null)
			{
				incomingRequest.put("Catalog_catalogId", catalogItem.getComp_id().getCatalogId());
				incomingRequest.put("CatalogPriceBrk_catalogId", catalogItem.getComp_id().getCatalogId());
				incomingRequest.put("CatalogPriceBrk_itemNumber", catalogItem.getComp_id().getItemNumber());
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
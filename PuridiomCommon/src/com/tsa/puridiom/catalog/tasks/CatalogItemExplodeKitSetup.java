package com.tsa.puridiom.catalog.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class CatalogItemExplodeKitSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			String	itemNumber = (String) incomingRequest.get("CatalogItem_itemNumber");
			String	catalogId = (String) incomingRequest.get("CatalogItem_catalogId");

			incomingRequest.put("KitItem_parentCatalogId", catalogId);
			incomingRequest.put("KitItem_parentItemNumber", itemNumber);
		}
		catch(Exception e) {
			throw e;
		}
					
		this.status = Status.SUCCEEDED;
		return result;
	}
}
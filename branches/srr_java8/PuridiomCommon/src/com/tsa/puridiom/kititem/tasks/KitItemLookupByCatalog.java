package com.tsa.puridiom.kititem.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class KitItemLookupByCatalog extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		Object itemNumberObj = incomingRequest.get("KitItem_childItemNumber");
		Object catalogIdObj = incomingRequest.get("KitItem_childCatalogId");
		Object quantityObj = incomingRequest.get("KitItem_childQuantity");

		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("kititem-update-by-id.xml");

		if (itemNumberObj instanceof String[])
		{
			String parentCatalogId = (String) incomingRequest.get("KitItem_parentCatalogId");
			String parentItemNumber = (String) incomingRequest.get("KitItem_parentItemNumber");

			String itemNumbers[] = (String[]) itemNumberObj;
			String catalogIds[] = (String[]) catalogIdObj;
			String quantities[] = (String[]) quantityObj;

			for (int i=0; i < itemNumbers.length; i++)
			{
				String itemNumber = itemNumbers[i];
				String catalogId = catalogIds[i];
				String quantity = quantities[i];

				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));

				updateParameters.put("formtype", incomingRequest.get("formtype"));
				updateParameters.put("KitItem_parentCatalogId", parentCatalogId);
				updateParameters.put("KitItem_parentItemNumber", parentItemNumber);
				updateParameters.put("KitItem_childCatalogId", catalogId);
				updateParameters.put("KitItem_childItemNumber", itemNumber);
				updateParameters.put("KitItem_childQuantity", quantity);

				process.executeProcess(updateParameters);

				if (process.getStatus() < Status.COMPLETED)
				{
					throw new Exception("KitItemLookup failed.  (ChildCatalogId = " + catalogId + "; ChildItemNumber: " + itemNumber);
				}
			}
		}
		else {
			if (!incomingRequest.containsKey("Catalog_catalogId"))
			{
				incomingRequest.put("Catalog_catalogId", incomingRequest.get("CatalogItem_catalogId"));
			}

			incomingRequest.put("createAction", "SAVE");
			process.executeProcess(incomingRequest);

			if (process.getStatus() < Status.COMPLETED)
			{
				throw new Exception("KitItemLookup failed.");
			}
		}

		this.status = Status.SUCCEEDED;
		return result;
	}
}
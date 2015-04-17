package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class CatalogItemExplodeKit extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		List	kitItemList = (List) incomingRequest.get("kitItemList");

		String	icHeader = (String) incomingRequest.get("icHeader");
		String	quantity = (String) incomingRequest.get("quantity");

		if (Utility.isEmpty(icHeader)) {
			throw new Exception("The ic header was not found.");
		}

		if (kitItemList == null || kitItemList.size() == 0) {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("line-update-from-lookup.xml");

			String	itemNumber = (String) incomingRequest.get("CatalogItem_itemNumber");
			String	catalogId = (String) incomingRequest.get("CatalogItem_catalogId");

			process.executeProcess(incomingRequest);

			if (process.getStatus() < Status.SUCCEEDED) {
				throw new Exception("Line Update From Lookup failed.  (CatalogId = " + catalogId + "; ItemNumber: " + itemNumber);
			}
		} else {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("catalogitem-lookup-by-id.xml");

			for (int i=0; i < kitItemList.size(); i++) {
				KitItem kitItem = (KitItem) kitItemList.get(i);
				String	itemNumber = kitItem.getComp_id().getChildItemNumber();
				String	catalogId = kitItem.getComp_id().getChildCatalogId();
				BigDecimal bdKitQty = kitItem.getChildQuantity();
				BigDecimal bdQuantity = new BigDecimal(quantity);

				bdQuantity = bdQuantity.multiply(bdKitQty);

				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
                updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("icHeader", icHeader);
				updateParameters.put("formtype", incomingRequest.get("formtype"));
				updateParameters.put("CatalogItem_itemNumber", itemNumber);
				updateParameters.put("CatalogItem_catalogId", catalogId);
				updateParameters.put("Catalog_catalogId", catalogId);
				updateParameters.put("quantity", bdQuantity.toString());
				updateParameters.put("createAction", "SAVE");

				process.executeProcess(updateParameters);

				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("CatalogItemLookup failed.  (CatalogId = " + catalogId + "; ItemNumber: " + itemNumber);
				}
			}
		}

		this.status = Status.SUCCEEDED;
		return result;
	}
}
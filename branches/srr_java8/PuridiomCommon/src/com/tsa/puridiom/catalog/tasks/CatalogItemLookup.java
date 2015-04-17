package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class CatalogItemLookup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		Object itemNumberObj = incomingRequest.get("CatalogItem_itemNumber");
		Object catalogIdObj = incomingRequest.get("CatalogItem_catalogId");
        Object quantityObj = incomingRequest.get("quantity");
		Object descriptionObj = incomingRequest.get("RecentItem_description");
        Object warehouseObj = incomingRequest.get("XrefCombo_code1");
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("catalogitem-lookup-by-id.xml");

		String	icHeader = (String) incomingRequest.get("icHeader");

		if (Utility.isEmpty(icHeader)) {
			throw new Exception("The ic header was not found.");
		}

		if (itemNumberObj instanceof String[]) {
			String itemNumbers[] = (String[]) itemNumberObj;
			String catalogIds[] = (String[]) catalogIdObj;
			String quantities[] = (String[]) quantityObj;
			String descriptions[] = (String[]) descriptionObj;
            String warehouses[];

            if (warehouseObj instanceof String[]) {
                warehouses = (String[]) warehouseObj;
            } else {
                warehouses = new String[itemNumbers.length];
            }

			for (int i=0; i < itemNumbers.length; i++) {
				String itemNumber = itemNumbers[i];
				String catalogId = catalogIds[i];
				String quantity = quantities[i];
                String warehouse = warehouses[i];

                if (!HiltonUtility.isEmpty(catalogId) && !HiltonUtility.isEmpty(itemNumber)) {
					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					updateParameters.put("userDateFormat", incomingRequest.get("userDateFormat"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					updateParameters.put("icHeader", icHeader);
					updateParameters.put("formtype", incomingRequest.get("formtype"));
					updateParameters.put("CatalogItem_itemNumber", itemNumber);
					updateParameters.put("CatalogItem_catalogId", catalogId);
					updateParameters.put("Catalog_catalogId", catalogId);
					updateParameters.put("warehouse", warehouse);
	                updateParameters.put("quantity", quantity);
					updateParameters.put("createAction", "SAVE");
					updateParameters.put("userNameUdf1",incomingRequest.get("userNameUdf1"));
					updateParameters.put("userNameUdf2",incomingRequest.get("userNameUdf2"));
					updateParameters.put("userNameUdf3",incomingRequest.get("userNameUdf3"));
					updateParameters.put("userNameUdf4",incomingRequest.get("userNameUdf4"));
					updateParameters.put("RequisitionHeader_requisitionType", incomingRequest.get("RequisitionHeader_requisitionType"));
					if (descriptions != null)
					{
						String description = descriptions[i];
						updateParameters.put("RecentItem_description", description);
					}
	
					process.executeProcess(updateParameters);
	
					if (process.getStatus() < Status.COMPLETED) {
						throw new Exception("CatalogItemLookup failed.  (CatalogId = " + catalogId + "; ItemNumber: " + itemNumber);
					}
                }
			}
		}
		else {
			if (!incomingRequest.containsKey("Catalog_catalogId")) {
				incomingRequest.put("Catalog_catalogId", incomingRequest.get("CatalogItem_catalogId"));
			}
			if (incomingRequest.containsKey("RecentItem_description")) {
				incomingRequest.put("RecentItem_description", incomingRequest.get("RecentItem_description"));
			}
            if (incomingRequest.containsKey("XrefCombo_code1")) {
                incomingRequest.put("warehouse", incomingRequest.get("XrefCombo_code1"));
            }
            incomingRequest.put("createAction", "SAVE");
			process.executeProcess(incomingRequest);

			if (process.getStatus() < Status.COMPLETED) {
				throw new Exception("CatalogItemLookup failed.");
			}
		}

		this.status = Status.SUCCEEDED;
		return result;
	}
}
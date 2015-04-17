package com.tsa.puridiom.requisitionline.tasks;

import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RequisitionLineItemLookup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		String	itemNumber = (String) incomingRequest.get("RequisitionLine_itemNumber");
		String	itemLocation = (String) incomingRequest.get("RequisitionLine_itemLocation");
		String	catalogId = (String) incomingRequest.get("RequisitionLine_catalogId");
		String	icHeader = "";
		String quantity = (String) incomingRequest.get("quantity");
		
		if (incomingRequest.containsKey("RequisitionLine_icReqHeader")) {
			icHeader = (String) incomingRequest.get("RequisitionLine_icReqHeader");
		}
		else if (incomingRequest.containsKey("RequisitionHeader_icReqHeader")) {
			icHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
		}
		else if (incomingRequest.containsKey("icHeader")) {
			icHeader = (String) incomingRequest.get("icHeader");
		}
		
		if (Utility.isEmpty(itemNumber)) {
			//throw new Exception ("Item number was not specified.");
		}
		if (!Utility.isEmpty(itemLocation)) {
			incomingRequest.put("InvLocation_itemLocation", itemLocation);
		}
		incomingRequest.put("InvItem_itemNumber", itemNumber);
		
		if (!Utility.isEmpty(catalogId)) {
			incomingRequest.put("Catalog_catalogId", catalogId);
			incomingRequest.put("CatalogItem_catalogId", catalogId);
		}
		if (!incomingRequest.containsKey("quantity") || Utility.isEmpty(quantity)) {
		    incomingRequest.put("quantity", (String) incomingRequest.get("RequisitionLine_quantity"));
		}
		incomingRequest.put("CatalogItem_itemNumber", itemNumber);
		incomingRequest.put("RequisitionHeader_icReqHeader", icHeader);
		incomingRequest.put("RequisitionLine_icReqHeader", icHeader);
		incomingRequest.put("icHeader", icHeader);
					
		this.status = Status.SUCCEEDED;
		return result;
	}
}
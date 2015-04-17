package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqLineItemLookup extends Task {
	
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		String	itemLocation = (String) incomingRequest.get("RfqLine_itemLocation");
		String	itemNumber = (String) incomingRequest.get("RfqLine_itemNumber");
		String	catalogId = (String) incomingRequest.get("RfqLine_catalogId");
		String	icHeader = "";
				
		if (incomingRequest.containsKey("RfqLine_icRfqHeader")) {
			icHeader = (String) incomingRequest.get("RfqLine_icRfqHeader");
		}
		else if (incomingRequest.containsKey("RfqHeader_icRfqHeader")) {
			icHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader");
		}
		else if (incomingRequest.containsKey("icHeader")) {
			icHeader = (String) incomingRequest.get("icHeader");
		}

		if (Utility.isEmpty(itemNumber)) {
			throw new Exception ("Item number was not specified.");			
		}
		if (!Utility.isEmpty(itemLocation)) {
			incomingRequest.put("InvLocation_itemLocation", itemLocation);
		}
		incomingRequest.put("InvItem_itemNumber", itemNumber);
		
		if (!Utility.isEmpty(catalogId)) {
			incomingRequest.put("Catalog_catalogId", catalogId);
			incomingRequest.put("CatalogItem_catalogId", catalogId);
		}
		incomingRequest.put("CatalogItem_itemNumber", itemNumber);
		incomingRequest.put("RfqHeader_icRfqHeader", icHeader);
		incomingRequest.put("RfqLine_icRfqHeader", icHeader);
		incomingRequest.put("icHeader", icHeader);

			
		this.status = Status.SUCCEEDED;
		return result;
	}
}
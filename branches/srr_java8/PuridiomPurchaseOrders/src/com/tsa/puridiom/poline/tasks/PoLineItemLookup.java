package com.tsa.puridiom.poline.tasks;

import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class PoLineItemLookup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			
			String	itemNumber = (String) incomingRequest.get("PoLine_itemNumber");
			String	itemLocation = (String) incomingRequest.get("PoLine_itemLocation");
			String	catalogId = (String) incomingRequest.get("PoLine_catalogId");
			String	icHeader = "";
			String quantity = (String) incomingRequest.get("quantity");
					
			if (incomingRequest.containsKey("PoLine_icPoHeader")) 
			{
				icHeader = (String) incomingRequest.get("PoLine_icPoHeader");
			}
			else if (incomingRequest.containsKey("PoHeader_icPoHeader")) 
			{
				icHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
			}
			else if (incomingRequest.containsKey("icHeader")) 
			{
				icHeader = (String) incomingRequest.get("icHeader");
			}
			
			if (Utility.isEmpty(itemNumber)) 
			{
				throw new TsaException("Item number was not specified.");			
			}
			if (!Utility.isEmpty(itemLocation)) 
			{
				incomingRequest.put("InvLocation_itemLocation", itemLocation);
			}
			incomingRequest.put("InvItem_itemNumber", itemNumber);
			
			if (!Utility.isEmpty(catalogId)) 
			{
				incomingRequest.put("Catalog_catalogId", catalogId);
				incomingRequest.put("CatalogItem_catalogId", catalogId);
			}
			if (Utility.isEmpty(quantity)) {
			    incomingRequest.put("quantity", (String) incomingRequest.get("PoLine_quantity"));
			}
			incomingRequest.put("CatalogItem_itemNumber", itemNumber);
			incomingRequest.put("PoHeader_icPoHeader", icHeader);
			incomingRequest.put("PoLine_icPoHeader", icHeader);
			incomingRequest.put("icHeader", icHeader);
						
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.SUCCEEDED;
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}
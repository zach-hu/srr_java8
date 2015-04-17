package com.tsa.puridiom.catalog.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.*;

public class CatalogItemUpdateFromLineSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		String docType = (String) incomingRequest.get("docType");
		String catalogId = "";
		String umCode = "";
		String cost = "";
		String itemNumber = "";
		
		try
		{
			if(docType.equals("PO")){
				catalogId = (String) incomingRequest.get("PoLine_catalogId");
				umCode = (String) incomingRequest.get("PoLine_umCode");
				cost = (String) incomingRequest.get("PoLine_unitPrice");
				itemNumber = (String) incomingRequest.get("PoLine_itemNumber");
			}
			else if(docType.equals("RQ")){
				catalogId = (String) incomingRequest.get("RequisitionLine_catalogId");
				umCode = (String) incomingRequest.get("RequisitionLine_umCode");
				cost = (String) incomingRequest.get("RequisitionLine_unitPrice");
				itemNumber = (String) incomingRequest.get("RequisitionLine_itemNumber");
			}
			
			incomingRequest.put("catalogId", catalogId);
	        incomingRequest.put("umCode", umCode);
	        incomingRequest.put("cost", cost);
	        incomingRequest.put("itemNumber", itemNumber);

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
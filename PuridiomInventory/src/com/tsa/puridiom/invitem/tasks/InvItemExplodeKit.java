package com.tsa.puridiom.invitem.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class InvItemExplodeKit extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		List	kitItemList = (List) incomingRequest.get("kitItemList");
		Object quantityObj = incomingRequest.get("quantity");
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("invitem-lookup-by-id.xml");

		String	icHeader = (String) incomingRequest.get("icHeader");
		String	quantity = (String) incomingRequest.get("quantity");
			
		if (Utility.isEmpty(icHeader)) {
			throw new Exception("The ic header was not found.");
		}
		
		if (kitItemList != null) {
			for (int i=0; i < kitItemList.size(); i++) {
				KitItem kitItem = (KitItem) kitItemList.get(i);
				String	itemNumber = kitItem.getComp_id().getChildItemNumber();
				String	itemLocation = kitItem.getComp_id().getChildCatalogId();
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
				updateParameters.put("RequisitionHeader_requisitionType", incomingRequest.get("RequisitionHeader_requisitionType"));
				updateParameters.put("InvItem_itemNumber", itemNumber);
				updateParameters.put("InvLocation_itemLocation", itemLocation);
				updateParameters.put("quantity", bdQuantity.toString());
				updateParameters.put("createAction", "SAVE");
	
				process.executeProcess(updateParameters);
				
				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("InvItemLookup failed.  (ItemLocation = " + itemLocation + "; ItemNumber: " + itemNumber);
				}
			}
		}
					
		this.status = Status.SUCCEEDED;
		return result;
	}
}
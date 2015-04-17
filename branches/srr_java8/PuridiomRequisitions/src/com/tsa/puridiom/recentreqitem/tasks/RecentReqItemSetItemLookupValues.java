package com.tsa.puridiom.recentreqitem.tasks;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RecentReqItemSetItemLookupValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		Object itemNumberObj = incomingRequest.get("RecentReqItem_itemNumber");
		Object catalogIdObj = incomingRequest.get("RecentReqItem_itemLocation");
		Object descriptionObj = incomingRequest.get("RecentReqItem_description");
		incomingRequest.put("CatalogItem_itemNumber", itemNumberObj);
		incomingRequest.put("CatalogItem_catalogId", catalogIdObj);
		incomingRequest.put("RecentItem_description", descriptionObj);
		
		if (itemNumberObj instanceof String[]) {
		    String itemNumberStr[] = (String[]) itemNumberObj;
		    if (itemNumberStr != null) {
		        String qty[] = new String[itemNumberStr.length];
		        for (int i=0; i < itemNumberStr.length; i++) {
		            String itemNumber = itemNumberStr[i];
		            if (!Utility.isEmpty(itemNumber)) {
		                qty[i] = "1";
		            } else {
		                qty[i] = "0";
		            }
		        }
		        incomingRequest.put("quantity", qty);
		    }
		} else {
		    incomingRequest.put("quantity", "1");
		}

		this.status = Status.SUCCEEDED;
		return result;
	}
}
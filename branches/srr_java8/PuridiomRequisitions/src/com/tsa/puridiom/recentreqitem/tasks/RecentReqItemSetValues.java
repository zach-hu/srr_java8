package com.tsa.puridiom.recentreqitem.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RecentReqItemSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RecentReqItemPK comp_id = new RecentReqItemPK();
			RecentReqItem recentReqItem = (RecentReqItem) incomingRequest.get("recentReqItem");
			if (recentReqItem == null)
			{
				recentReqItem = new RecentReqItem();
			}

			if (incomingRequest.containsKey("RecentReqItem_requisitionerCode"))
			{
				String requisitionerCode = (String ) incomingRequest.get("RecentReqItem_requisitionerCode");
				comp_id.setRequisitionerCode(requisitionerCode);
			}
			if (incomingRequest.containsKey("RecentReqItem_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("RecentReqItem_itemNumber");
				comp_id.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("RecentReqItem_itemLocation"))
			{
				String itemLocation = (String ) incomingRequest.get("RecentReqItem_itemLocation");
				comp_id.setItemLocation(itemLocation);
			}
			if (incomingRequest.containsKey("RecentReqItem_itemSource"))
			{
				String itemSource = (String ) incomingRequest.get("RecentReqItem_itemSource");
				recentReqItem.setItemSource(itemSource);
			}
			if (incomingRequest.containsKey("RecentReqItem_description"))
			{
				String description = (String ) incomingRequest.get("RecentReqItem_description");
				recentReqItem.setDescription(description);
			}
			if (incomingRequest.containsKey("RecentReqItem_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("RecentReqItem_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				recentReqItem.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("RecentReqItem_requestCount"))
			{
				String	requestCountString = (String) incomingRequest.get("RecentReqItem_requestCount");
				if (Utility.isEmpty(requestCountString)) {
				    requestCountString = "0";
				}
			    BigDecimal requestCount = new BigDecimal(requestCountString);
			    recentReqItem.setRequestCount(requestCount);
			}
			recentReqItem.setComp_id(comp_id);

			result = recentReqItem;
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
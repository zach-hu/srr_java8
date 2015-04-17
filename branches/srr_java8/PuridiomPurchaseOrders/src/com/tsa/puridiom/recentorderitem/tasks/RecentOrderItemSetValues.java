package com.tsa.puridiom.recentorderitem.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RecentOrderItemSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RecentOrderItemPK comp_id = new RecentOrderItemPK();
			RecentOrderItem recentOrderItem = (RecentOrderItem) incomingRequest.get("recentOrderItem");
			if (recentOrderItem == null)
			{
				recentOrderItem = new RecentOrderItem();
			}

			if (incomingRequest.containsKey("RecentOrderItem_buyerCode"))
			{
				String buyerCode = (String ) incomingRequest.get("RecentOrderItem_buyerCode");
				comp_id.setBuyerCode(buyerCode);
			}
			if (incomingRequest.containsKey("RecentOrderItem_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("RecentOrderItem_itemNumber");
				comp_id.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("RecentOrderItem_itemLocation"))
			{
				String itemLocation = (String ) incomingRequest.get("RecentOrderItem_itemLocation");
				comp_id.setItemLocation(itemLocation);
			}
			if (incomingRequest.containsKey("RecentOrderItem_itemSource"))
			{
				String itemSource = (String ) incomingRequest.get("RecentOrderItem_itemSource");
				recentOrderItem.setItemSource(itemSource);
			}
			if (incomingRequest.containsKey("RecentOrderItem_description"))
			{
				String description = (String ) incomingRequest.get("RecentOrderItem_description");
				recentOrderItem.setDescription(description);
			}
			if (incomingRequest.containsKey("RecentOrderItem_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("RecentOrderItem_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				recentOrderItem.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("RecentOrderItem_orderCount"))
			{
				String orderCountString = (String) incomingRequest.get("RecentOrderItem_orderCount");
				if (Utility.isEmpty(orderCountString))
				{
				    orderCountString = "0";
				}
				BigDecimal orderCount = new BigDecimal ( orderCountString );
				recentOrderItem.setOrderCount(orderCount);
			}
			recentOrderItem.setComp_id(comp_id);

			result = recentOrderItem;
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
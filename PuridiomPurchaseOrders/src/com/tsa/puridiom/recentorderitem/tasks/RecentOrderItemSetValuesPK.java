package com.tsa.puridiom.recentorderitem.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RecentOrderItemSetValuesPK
{
	public void setValues(Map incomingRequest, RecentOrderItem recentorderitem ) throws Exception
	{
		try
		{
			String buyerCode = (String ) incomingRequest.get("RecentOrderItem_buyerCode");
			String itemNumber = (String ) incomingRequest.get("RecentOrderItem_itemNumber");
			String itemLocation = (String ) incomingRequest.get("RecentOrderItem_itemLocation");
			RecentOrderItemPK comp_id = new RecentOrderItemPK();
			comp_id.setBuyerCode(buyerCode);
			comp_id.setItemLocation(itemLocation);
			comp_id.setItemNumber(itemNumber);
			recentorderitem.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
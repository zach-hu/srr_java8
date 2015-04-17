package com.tsa.puridiom.recentreqitem.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RecentReqItemSetValuesPK
{
	public void setValues(Map incomingRequest, RecentReqItem recentreqitem ) throws Exception
	{
		try
		{
			String requisitionerCode = (String ) incomingRequest.get("RecentReqItem_requisitionerCode");
			String itemNumber = (String ) incomingRequest.get("RecentReqItem_itemNumber");
			String itemLocation = (String ) incomingRequest.get("RecentReqItem_itemLocation");
			RecentReqItemPK comp_id = new RecentReqItemPK();
			comp_id.setItemLocation(itemLocation);
			comp_id.setItemNumber(itemNumber);
			comp_id.setRequisitionerCode(requisitionerCode);
			recentreqitem.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
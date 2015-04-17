package com.tsa.puridiom.recentorder.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.Map;

public class RecentOrderSetValuesPK
{
	public void setValues(Map incomingRequest, RecentOrder recentorder ) throws Exception
	{
		try
		{
			String buyerCode = (String ) incomingRequest.get("RecentOrder_buyerCode");
			String icPoHeaderString = (String) incomingRequest.get("RecentOrder_icPoHeader");
			if (Utility.isEmpty(icPoHeaderString))
			{
			    icPoHeaderString = "0";
			}
			BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
			
			RecentOrderPK comp_id = new RecentOrderPK();
			comp_id.setBuyerCode(buyerCode);
			comp_id.setIcPoHeader(icPoHeader);
			recentorder.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
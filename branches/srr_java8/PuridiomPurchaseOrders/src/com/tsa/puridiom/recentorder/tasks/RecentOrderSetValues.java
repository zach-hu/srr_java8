package com.tsa.puridiom.recentorder.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RecentOrderSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RecentOrderPK comp_id = new RecentOrderPK();
			RecentOrder recentOrder = (RecentOrder) incomingRequest.get("recentOrder");
			if (recentOrder == null)
			{
				recentOrder = new RecentOrder();
			}

			if (incomingRequest.containsKey("RecentOrder_buyerCode"))
			{
				String buyerCode = (String ) incomingRequest.get("RecentOrder_buyerCode");
				if(Utility.isEmpty(buyerCode))
				{
					buyerCode = "SYSADM";
				}
				comp_id.setBuyerCode(buyerCode);
			}
			if (incomingRequest.containsKey("RecentOrder_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("RecentOrder_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
				    icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				comp_id.setIcPoHeader(icPoHeader);
			}
			if (incomingRequest.containsKey("RecentOrder_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("RecentOrder_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				recentOrder.setDateEntered(dateEntered);
			}
			recentOrder.setComp_id(comp_id);

			result = recentOrder;
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
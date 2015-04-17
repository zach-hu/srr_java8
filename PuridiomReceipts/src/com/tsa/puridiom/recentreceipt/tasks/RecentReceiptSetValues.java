package com.tsa.puridiom.recentreceipt.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RecentReceiptSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RecentReceiptPK comp_id = new RecentReceiptPK();
			RecentReceipt recentReceipt = (RecentReceipt) incomingRequest.get("recentReceipt");
			if (recentReceipt == null)
			{
				recentReceipt = new RecentReceipt();
			}

			if (incomingRequest.containsKey("RecentReceipt_receivedBy"))
			{
				String receivedBy = (String ) incomingRequest.get("RecentReceipt_receivedBy");
				comp_id.setReceivedBy(receivedBy);
			}
			if (incomingRequest.containsKey("RecentReceipt_icRecHeader"))
			{
				String icRecHeaderString = (String) incomingRequest.get("ReceiptHeader_icRecHeader");
				if (Utility.isEmpty(icRecHeaderString))
				{
					icRecHeaderString = "0";
				}
				BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
				comp_id.setIcRecHeader(icRecHeader);
			}
			if (incomingRequest.containsKey("RecentReceipt_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("RecentReceipt_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				recentReceipt.setDateEntered(dateEntered);
			}
			recentReceipt.setComp_id(comp_id);

			result = recentReceipt;
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
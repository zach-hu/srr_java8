package com.tsa.puridiom.recentreceipt.tasks;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class RecentReceiptSetupFromReceiptHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
		    if (receiptHeader == null) {
		        throw new Exception("RecentReceipt cannot be setup.  ReceiptHeader was not found.");
		    }
		    incomingRequest.put("RecentReceipt_receivedBy", receiptHeader.getReceivedBy());
		    incomingRequest.put("RecentReceipt_icRecHeader", receiptHeader.getIcRecHeader().toString());
		    incomingRequest.put("RecentReceipt_dateEntered", Dates.today("", ""));
		    // Use date in default system time zone to ensure proper sorting

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
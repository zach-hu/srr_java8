package com.tsa.puridiom.recentrfq.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class RecentRfqSetupFromRfqHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
		    if (rfqHeader == null) {
		        throw new Exception("RecentRfq cannot be setup.  RfqHeader was not found.");
		    }
		    incomingRequest.put("RecentRfq_buyerCode", rfqHeader.getBuyer());
		    incomingRequest.put("RecentRfq_icRfqHeader", rfqHeader.getIcRfqHeader().toString());
		    incomingRequest.put("RecentRfq_dateEntered", Dates.today("", ""));
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
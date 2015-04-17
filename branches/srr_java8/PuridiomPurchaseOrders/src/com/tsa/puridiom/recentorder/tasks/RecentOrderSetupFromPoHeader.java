package com.tsa.puridiom.recentorder.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class RecentOrderSetupFromPoHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
		    if (poHeader == null) {
		        throw new Exception("RecentOrder cannot be setup.  PoHeader was not found.");
		    }
		    String	buyer = poHeader.getBuyerCode();
		    if (Utility.isEmpty(buyer)) {
		        buyer = poHeader.getOwner();
		    }
		    incomingRequest.put("RecentOrder_buyerCode", buyer);
		    incomingRequest.put("RecentOrder_icPoHeader", poHeader.getIcPoHeader().toString());
		    incomingRequest.put("RecentOrder_dateEntered", Dates.today("", ""));
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
package com.tsa.puridiom.recentreqitem.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class RecentReqItemIncrementCount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RecentReqItem recentReqItem = (RecentReqItem) incomingRequest.get("recentReqItem");
			if (recentReqItem == null)
			{
				recentReqItem = new RecentReqItem();
			}

		    BigDecimal requestCount = recentReqItem.getRequestCount();
			recentReqItem.setRequestCount(requestCount.add(new BigDecimal("1")));

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
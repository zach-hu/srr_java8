package com.tsa.puridiom.recentorderitem.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class RecentOrderItemIncrementCount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RecentOrderItem recentOrderItem = (RecentOrderItem) incomingRequest.get("recentOrderItem");
			if (recentOrderItem == null)
			{
			    recentOrderItem = new RecentOrderItem();
			}

		    BigDecimal requestCount = recentOrderItem.getOrderCount();
		    recentOrderItem.setOrderCount(requestCount.add(new BigDecimal("1")));

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
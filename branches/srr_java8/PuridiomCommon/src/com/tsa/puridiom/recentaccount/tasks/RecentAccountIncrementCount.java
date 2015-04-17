package com.tsa.puridiom.recentaccount.tasks;

import com.tsa.puridiom.entity.RecentAccount;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class RecentAccountIncrementCount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RecentAccount recentAccount = (RecentAccount) incomingRequest.get("recentAccount");
			if (recentAccount == null)
			{
				recentAccount = new RecentAccount();
			}

		    BigDecimal accountCount = recentAccount.getAccountCount();
		    recentAccount.setAccountCount(accountCount.add(new BigDecimal("1")));

			result = recentAccount;
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
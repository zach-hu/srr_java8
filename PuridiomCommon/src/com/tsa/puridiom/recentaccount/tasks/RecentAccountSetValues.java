package com.tsa.puridiom.recentaccount.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RecentAccountSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RecentAccountPK comp_id = new RecentAccountPK();
			RecentAccount recentAccount = (RecentAccount) incomingRequest.get("recentAccount");
			if (recentAccount == null)
			{
				recentAccount = new RecentAccount();
			}

			if (incomingRequest.containsKey("RecentAccount_userId"))
			{
				String userId = (String) incomingRequest.get("RecentAccount_userId");
				comp_id.setUserId(userId);
			}
			if (incomingRequest.containsKey("RecentAccount_accountString"))
			{
				String accountString = (String) incomingRequest.get("RecentAccount_accountString");
				comp_id.setAccountString(accountString);
			}
			if (incomingRequest.containsKey("RecentAccount_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("RecentAccount_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				recentAccount.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("RecentAccount_accountCount"))
			{
				String accountCountString = (String) incomingRequest.get("RecentAccount_accountCount");
				if (Utility.isEmpty(accountCountString))
				{
					accountCountString = "0";
				}
				BigDecimal accountCount = new BigDecimal ( accountCountString );
				recentAccount.setAccountCount(accountCount);
			}
			recentAccount.setComp_id(comp_id);

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
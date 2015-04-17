package com.tsa.puridiom.recentaccount.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class RecentAccountSetupFromAccount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    Account account = (Account) incomingRequest.get("account");
		    String oid = (String) incomingRequest.get("organizationId");
		    String userId = (String) incomingRequest.get("userId");

		    if (account == null)
		    {
		        throw new Exception ("Recent Account cannot be setup.  Account was not found.");
		    }

		    incomingRequest.put("RecentAccount_userId", userId);
			incomingRequest.put("RecentAccount_accountString", HiltonUtility.getAcctString(account, oid));
			incomingRequest.put("RecentAccount_dateEntered", Dates.today("", ""));
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
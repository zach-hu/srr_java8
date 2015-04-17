package com.tsa.puridiom.recentaccount.tasks;

import com.tsa.puridiom.entity.Account;
import com.tsagate.foundation.processengine.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecentAccountAddListFromAccount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    List	accountList = (List) incomingRequest.get("accountList");

		    if (accountList == null) {
		        throw new Exception ("Recent Account cannot be added.  AccountList was not found.");
		    }

		    Map requestParameters = new HashMap();

		    requestParameters.put("dbsession", incomingRequest.get("dbsession"));
		    requestParameters.put("userId", incomingRequest.get("userId"));
				 requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
		    requestParameters.put("organizationId", incomingRequest.get("organizationId"));

		    for (int i = 0; i < accountList.size(); i++)
		    {
		        Account account = (Account) accountList.get(i);
		        requestParameters.put("account", account);

		        PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("recentaccount-add.xml");

				process.executeProcess(requestParameters);

				if (process.getStatus() < Status.SUCCEEDED)
				{
					throw new Exception("RecentAccount Add process failed.");
				}

		    }

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
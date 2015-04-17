package com.tsa.puridiom.account.tasks;

import java.util.Map;
import com.tsa.puridiom.entity.Commodity;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AccountCommoditySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			Commodity commodity = (Commodity) incomingRequest.get("commodity");
			if (commodity != null)
			{
				String icAccount = commodity.getIcAccount().toString();
				incomingRequest.put("Account_icHeader", icAccount);
			}
			else
			{
				incomingRequest.put("Account_icHeader", "0");
			}
			incomingRequest.put("Account_icLine", "0");
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
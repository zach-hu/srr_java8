package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class AccountSetValuesFromExternal extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String formtype = (String) incomingRequest.get("formtype");
			if (formtype.equalsIgnoreCase("REQ"))
			{
				incomingRequest.put("RequisitionLine_icAccount", incomingRequest.get("Account_icLine"));
				incomingRequest.put("Account_accountType", "RQL");
			}
			else if (formtype.equalsIgnoreCase("PO"))
			{
				incomingRequest.put("PoLine_icAccount", incomingRequest.get("Account_icLine"));
				incomingRequest.put("Account_accountType", "POL");
			}
			else if (formtype.equalsIgnoreCase("DSB"))
			{
				incomingRequest.put("DisbLine_icAccount", incomingRequest.get("Account_icLine"));
				incomingRequest.put("Account_accountType", "DBL");
			}
			incomingRequest.put("Account_allocPercent", "100");
			incomingRequest.put("Account_allocMethod", "PL");
			incomingRequest.put("Account_owner", (String) incomingRequest.get("userId"));

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
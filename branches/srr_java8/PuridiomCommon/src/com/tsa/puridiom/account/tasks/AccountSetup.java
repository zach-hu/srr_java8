package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class AccountSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("Account_icHeader", "0");
			incomingRequest.put("Account_icLine", "0");
			incomingRequest.put("Account_sequence", "0");
			incomingRequest.put("Account_accountType", "");
			incomingRequest.put("Account_fld1", "");
			incomingRequest.put("Account_fld2", "");
			incomingRequest.put("Account_fld3", "");
			incomingRequest.put("Account_fld4", "");
			incomingRequest.put("Account_fld5", "");
			incomingRequest.put("Account_fld6", "");
			incomingRequest.put("Account_fld7", "");
			incomingRequest.put("Account_fld8", "");
			incomingRequest.put("Account_fld9", "");
			incomingRequest.put("Account_fld10", "");
			incomingRequest.put("Account_fld11", "");
			incomingRequest.put("Account_fld12", "");
			incomingRequest.put("Account_fld13", "");
			incomingRequest.put("Account_fld14", "");
			incomingRequest.put("Account_fld15", "");
			incomingRequest.put("Account_allocPercent", "0");
			incomingRequest.put("Account_allocAmount", "0");
			incomingRequest.put("Account_accountTitle", "");
			incomingRequest.put("Account_dateEntered", Dates.today("", ""));
			incomingRequest.put("Account_dateExpires", Dates.today("", ""));
			incomingRequest.put("Account_status", "");
			incomingRequest.put("Account_owner", "");
			incomingRequest.put("Account_allocMethod", "");
			incomingRequest.put("Account_allocQty", "0");
			incomingRequest.put("Account_recQty", "0");
			incomingRequest.put("Account_icLastRec", "0");
			incomingRequest.put("Account_source", "");

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

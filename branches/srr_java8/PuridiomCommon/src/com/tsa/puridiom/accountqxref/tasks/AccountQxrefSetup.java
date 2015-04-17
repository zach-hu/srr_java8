package com.tsa.puridiom.accountqxref.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class AccountQxrefSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("AccountQxref_location", "");
			incomingRequest.put("AccountQxref_afe", "");
			incomingRequest.put("AccountQxref_wellFacility", "");
			incomingRequest.put("AccountQxref_costCenter", "");

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
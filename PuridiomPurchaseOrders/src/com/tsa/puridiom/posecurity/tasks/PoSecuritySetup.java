package com.tsa.puridiom.posecurity.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

public class PoSecuritySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("PoSecurity_poNumber", "");
			incomingRequest.put("PoSecurity_accessType", "");
			incomingRequest.put("PoSecurity_accessId", "");
			incomingRequest.put("PoSecurity_owner", "");
			incomingRequest.put("PoSecurity_dateEntered", Dates.today("", ""));
			incomingRequest.put("PoSecurity_dateChanged", Dates.today("", ""));
			incomingRequest.put("PoSecurity_lastChangedBy", "");
			incomingRequest.put("PoSecurity_timeZone", "");

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

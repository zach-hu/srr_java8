package com.tsa.puridiom.invmethod.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InvMethodSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InvMethod_methodId", "");
			incomingRequest.put("InvMethod_description", "");
			incomingRequest.put("InvMethod_notes", "");
			incomingRequest.put("InvMethod_dateEntered", Dates.today("", ""));
			incomingRequest.put("InvMethod_dateExpires", Dates.today("", ""));
			incomingRequest.put("InvMethod_owner", "");
			incomingRequest.put("InvMethod_status", "");

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
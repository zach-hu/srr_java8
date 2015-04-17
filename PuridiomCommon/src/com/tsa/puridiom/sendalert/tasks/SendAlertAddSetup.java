package com.tsa.puridiom.sendalert.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.util.Map;

public class SendAlertAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("SendAlert_queueid",ukg.getUniqueKey().toString());
			incomingRequest.put("SendAlert_status", "00");
			incomingRequest.put("SendAlert_dateadded",  Dates.today("yyyy/MM/dd", ""));
			incomingRequest.put("SendAlert_timeadded",  Dates.getNow(null, ""));
			incomingRequest.put("SendAlert_attempts", "0");
			incomingRequest.put("SendAlert_owner", (String)incomingRequest.get("userId"));

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
package com.tsa.puridiom.sendqueue.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class SendQueueAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("SendQueue_queueid",ukg.getUniqueKey().toString());
			incomingRequest.put("SendQueue_status", "00");
			incomingRequest.put("SendQueue_dateadded",  Dates.today("yyyy/MM/dd", ""));
			incomingRequest.put("SendQueue_timeadded",  Dates.getNow(null, ""));
			incomingRequest.put("SendQueue_attempts", "0");

			String	owner = (String) incomingRequest.get("SendQueue_owner");
			if (Utility.isEmpty(owner)) {
			    incomingRequest.put("SendQueue_owner", (String)incomingRequest.get("userId"));
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
package com.tsa.puridiom.invalternate.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InvAlternateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InvAlternate_icAlternate", "0");
			incomingRequest.put("InvAlternate_itemNumber", "");
			incomingRequest.put("InvAlternate_altItemNumber", "");
			incomingRequest.put("InvAlternate_dateEntered", Dates.today("", ""));
			incomingRequest.put("InvAlternate_owner", "");

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
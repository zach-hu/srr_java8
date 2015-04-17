package com.tsa.puridiom.itemcrossref.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class ItemCrossRefSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("ItemCrossRef_altItemNumber", "");
			incomingRequest.put("ItemCrossRef_description", "");
			incomingRequest.put("ItemCrossRef_itemNumber", "");
			incomingRequest.put("ItemCrossRef_source", "");
			incomingRequest.put("ItemCrossRef_dateEntered", Dates.today("", ""));
			incomingRequest.put("ItemCrossRef_dateExpires", Dates.today("", ""));
			incomingRequest.put("ItemCrossRef_status", "02");
			incomingRequest.put("ItemCrossRef_owner", (String) incomingRequest.get("userId"));

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
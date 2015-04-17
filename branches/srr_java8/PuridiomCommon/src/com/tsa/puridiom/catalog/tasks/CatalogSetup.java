package com.tsa.puridiom.catalog.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class CatalogSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			incomingRequest.put("Catalog_dateEntered", Dates.today("", userTimeZone));

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
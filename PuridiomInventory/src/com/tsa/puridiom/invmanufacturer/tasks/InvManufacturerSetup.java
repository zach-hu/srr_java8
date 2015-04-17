package com.tsa.puridiom.invmanufacturer.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InvManufacturerSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InvManufacturer_icManufacturer", "0");
			incomingRequest.put("InvManufacturer_itemNumber", "");
			incomingRequest.put("InvManufacturer_vendorId", "");
			incomingRequest.put("InvManufacturer_partNumber", "");
			incomingRequest.put("InvManufacturer_notes", "");
			incomingRequest.put("InvManufacturer_dateEntered", Dates.today("", ""));
			incomingRequest.put("InvManufacturer_owner", "");

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
package com.tsa.puridiom.bommanufacturer.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class BomManufacturerSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("BomManufacturer_icManufacturer", "0");
			incomingRequest.put("BomManufacturer_parentItem", "");
			incomingRequest.put("BomManufacturer_componentItem", "");
			incomingRequest.put("BomManufacturer_icComponent", "0");
			incomingRequest.put("BomManufacturer_vendorId", "");
			incomingRequest.put("BomManufacturer_vendorName", "");
			incomingRequest.put("BomManufacturer_methodId", "");
			incomingRequest.put("BomManufacturer_stageId", "");
			incomingRequest.put("BomManufacturer_partNumber", "");
			incomingRequest.put("BomManufacturer_dateEntered", Dates.today("", ""));
			incomingRequest.put("BomManufacturer_owner", "");

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
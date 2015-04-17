package com.tsa.puridiom.bommethod.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class BomMethodSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("BomMethod_icMethod", "0");
			incomingRequest.put("BomMethod_parentItem", "");
			incomingRequest.put("BomMethod_componentItem", "");
			incomingRequest.put("BomMethod_methodId", "");
			incomingRequest.put("BomMethod_batchSize", "0");
			incomingRequest.put("BomMethod_unitOfMeasure", "");
			incomingRequest.put("BomMethod_description", "");
			incomingRequest.put("BomMethod_notes", "");
			incomingRequest.put("BomMethod_dateEntered", Dates.today("", ""));
			incomingRequest.put("BomMethod_owner", "");

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
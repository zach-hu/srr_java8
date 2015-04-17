package com.tsa.puridiom.inspectioncrit.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InspectionCritSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InspectionCrit_inspectCode", "");
			incomingRequest.put("InspectionCrit_critNo", "");
			incomingRequest.put("InspectionCrit_description", "");
			incomingRequest.put("InspectionCrit_defaultFlag", "");
			incomingRequest.put("InspectionCrit_status", "");
			incomingRequest.put("InspectionCrit_owner", "");

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
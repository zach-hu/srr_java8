package com.tsa.puridiom.inspectionstd.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InspectionStdSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InspectionStd_standardCode", "");
			incomingRequest.put("InspectionStd_inspectType", "");
			incomingRequest.put("InspectionStd_inspectCode", "");
			incomingRequest.put("InspectionStd_critNo", "");
			incomingRequest.put("InspectionStd_description", "");
			incomingRequest.put("InspectionStd_defaultFlag", "");
			incomingRequest.put("InspectionStd_status", "");
			incomingRequest.put("InspectionStd_owner", "");
			incomingRequest.put("InspectionStd_lastChangeBy", "");
			incomingRequest.put("InspectionStd_cgdNo", "");
			incomingRequest.put("InspectionStd_cgdRev", "0");

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
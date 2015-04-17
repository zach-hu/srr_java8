package com.tsa.puridiom.inspectiondiscrep.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InspectionDiscrepSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InspectionDiscrep_icRecHeader", "0");
			incomingRequest.put("InspectionDiscrep_icRecLine", "0");
			incomingRequest.put("InspectionDiscrep_keySequence", "0");
			incomingRequest.put("InspectionDiscrep_icInspNo", "0");
			incomingRequest.put("InspectionDiscrep_icMsrLine", "0");
			incomingRequest.put("InspectionDiscrep_inspectCode", "");
			incomingRequest.put("InspectionDiscrep_inspRequirements", "");
			incomingRequest.put("InspectionDiscrep_inspDescription", "");
			incomingRequest.put("InspectionDiscrep_inspQuantity", "0");
			incomingRequest.put("InspectionDiscrep_status", "");
			incomingRequest.put("InspectionDiscrep_lastChangeBy", "");

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
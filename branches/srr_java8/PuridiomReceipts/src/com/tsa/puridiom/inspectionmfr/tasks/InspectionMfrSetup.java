package com.tsa.puridiom.inspectionmfr.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InspectionMfrSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InspectionMfr_icMfrNo", "0");
			incomingRequest.put("InspectionMfr_icInspNo", "0");
			incomingRequest.put("InspectionMfr_icMsrLine", "0");
			incomingRequest.put("InspectionMfr_documentType", "");
			incomingRequest.put("InspectionMfr_mfrName", "");
			incomingRequest.put("InspectionMfr_mfrNo", "");
			incomingRequest.put("InspectionMfr_mfrHeatLot", "");

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
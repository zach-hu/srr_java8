package com.tsa.puridiom.inspectionline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InspectionLineSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String today = Dates.today("", userTimeZone);

		try
		{
			incomingRequest.put("InspectionLine_icInspNo", "0");
			incomingRequest.put("InspectionLine_icInspLine", "0");
			incomingRequest.put("InspectionLine_inspectCode", "");
			incomingRequest.put("InspectionLine_seqNo", "0");
			incomingRequest.put("InspectionLine_critNo", "");
			incomingRequest.put("InspectionLine_standardCode", "");
			incomingRequest.put("InspectionLine_lockFlag", "");
			incomingRequest.put("InspectionLine_udf01", "");
			incomingRequest.put("InspectionLine_udf02", "");
			incomingRequest.put("InspectionLine_udf03", "");
			incomingRequest.put("InspectionLine_udf04", "");
			incomingRequest.put("InspectionLine_udf05", "");
			incomingRequest.put("InspectionLine_status", DocumentStatus.INSP_PENDING);
			incomingRequest.put("InspectionLine_owner", "");
			incomingRequest.put("InspectionLine_lastChangeBy", "");
			incomingRequest.put("InspectionLine_lastChanged", today);
			incomingRequest.put("InspectionLine_dateEntered", today);
			incomingRequest.put("InspectionLine_critDescription", "");

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
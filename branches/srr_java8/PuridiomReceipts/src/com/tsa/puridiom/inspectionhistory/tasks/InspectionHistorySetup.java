package com.tsa.puridiom.inspectionhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InspectionHistorySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InspectionHistory_icHistory", "0");
			incomingRequest.put("InspectionHistory_icRecLine", "0");
			incomingRequest.put("InspectionHistory_icInspNo", "0");
			incomingRequest.put("InspectionHistory_icMsrLine", "0");
			incomingRequest.put("InspectionHistory_recType", "");
			incomingRequest.put("InspectionHistory_quantity", "0");
			incomingRequest.put("InspectionHistory_area", "");
			incomingRequest.put("InspectionHistory_storage", "");
			incomingRequest.put("InspectionHistory_location", "");
			incomingRequest.put("InspectionHistory_status", "");
			incomingRequest.put("InspectionHistory_statusDate", "");
			incomingRequest.put("InspectionHistory_owner", "");
			incomingRequest.put("InspectionHistory_historyText", "");

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
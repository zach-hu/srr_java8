package com.tsa.puridiom.inspectiondispos.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InspectionDisposSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InspectionDispos_icRecHeader", "0");
			incomingRequest.put("InspectionDispos_icRecLine", "0");
			incomingRequest.put("InspectionDispos_keySequence", "0");
			incomingRequest.put("InspectionDispos_icInspNo", "0");
			incomingRequest.put("InspectionDispos_icMsrLine", "0");
			incomingRequest.put("InspectionDispos_disposition", "");
			incomingRequest.put("InspectionDispos_respGroup", "");
			incomingRequest.put("InspectionDispos_dispType", "");
			incomingRequest.put("InspectionDispos_dispQuantity", "0");
			incomingRequest.put("InspectionDispos_lastChangeBy", "");
			incomingRequest.put("InspectionDispos_description", "");

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
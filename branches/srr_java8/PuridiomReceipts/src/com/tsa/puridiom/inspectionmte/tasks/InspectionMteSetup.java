package com.tsa.puridiom.inspectionmte.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InspectionMteSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InspectionMte_icRecHeader", "0");
			incomingRequest.put("InspectionMte_icRecLine", "0");
			incomingRequest.put("InspectionMte_keySequence", "0");
			incomingRequest.put("InspectionMte_icInspNo", "0");
			incomingRequest.put("InspectionMte_icMsrLine", "0");
			incomingRequest.put("InspectionMte_mteNo", "");
			incomingRequest.put("InspectionMte_description", "");
			incomingRequest.put("InspectionMte_lastChangeBy", "");

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
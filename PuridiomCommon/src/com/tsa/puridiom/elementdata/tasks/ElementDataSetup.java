package com.tsa.puridiom.elementdata.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class ElementDataSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("ElementData_formId", "");
			incomingRequest.put("ElementData_icHeader", "0");
			incomingRequest.put("ElementData_icLine", "0");
			incomingRequest.put("ElementData_icSequence", "0");
			incomingRequest.put("ElementData_elementId", "");
			incomingRequest.put("ElementData_elementValue", "");

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
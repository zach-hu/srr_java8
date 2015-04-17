package com.tsa.puridiom.elementform.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class ElementFormSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("ElementForm_formId", "");
			incomingRequest.put("ElementForm_elementIndex", "0");
			incomingRequest.put("ElementForm_elementModule", "");
			incomingRequest.put("ElementForm_elementId", "");
			incomingRequest.put("ElementForm_elementType", "");
			incomingRequest.put("ElementForm_elementLength", "0");
			incomingRequest.put("ElementForm_elementCase", "");
			incomingRequest.put("ElementForm_elementFormat", "");
			incomingRequest.put("ElementForm_elementLabel", "");
			incomingRequest.put("ElementForm_elementDefault", "");
			incomingRequest.put("ElementForm_elementTb", "0");
			incomingRequest.put("ElementForm_elementTc", "0");

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
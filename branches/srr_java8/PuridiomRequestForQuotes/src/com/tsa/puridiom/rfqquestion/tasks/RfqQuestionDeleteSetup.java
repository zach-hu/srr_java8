package com.tsa.puridiom.rfqquestion.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqQuestionDeleteSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			if (!incomingRequest.containsKey("RfqQuestion_icRfqHeader"))
			{
				incomingRequest.put("RfqQuestion_icRfqHeader", incomingRequest.get("RfqHeader_icRfqHeader"));
			}

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
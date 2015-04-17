package com.tsa.puridiom.doctext.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class DocTextSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("DocText_icText", "0");
			incomingRequest.put("DocText_stdText", "");
			incomingRequest.put("DocText_referenceType", "");
			incomingRequest.put("DocText_idReference", "");

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
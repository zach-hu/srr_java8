package com.tsa.puridiom.stddocument.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class StdDocumentDownloadFileSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    incomingRequest.put("documentType", "internal");

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
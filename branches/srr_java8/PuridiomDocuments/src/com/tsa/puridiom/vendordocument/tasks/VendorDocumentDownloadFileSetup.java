package com.tsa.puridiom.vendordocument.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorDocumentDownloadFileSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    incomingRequest.put("documentType", "external");

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
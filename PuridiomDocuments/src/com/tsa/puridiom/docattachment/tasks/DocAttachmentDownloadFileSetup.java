package com.tsa.puridiom.docattachment.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class DocAttachmentDownloadFileSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			if (incomingRequest.get("documentType") == null)
			{
				incomingRequest.put("documentType", "internal");
			}

			this.status = Status.SUCCEEDED;
		} catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
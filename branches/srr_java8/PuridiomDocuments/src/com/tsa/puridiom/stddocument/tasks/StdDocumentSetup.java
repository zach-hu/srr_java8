package com.tsa.puridiom.stddocument.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class StdDocumentSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("StdDocument_fileName", "");
			incomingRequest.put("StdDocument_title", "");
			incomingRequest.put("StdDocument_description", "");
			incomingRequest.put("StdDocument_docType", "");
			incomingRequest.put("StdDocument_datePosted", Dates.today(""));
			incomingRequest.put("StdDocument_hits", "0");

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
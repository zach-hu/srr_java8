package com.tsa.puridiom.report.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ReportSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("Report_reportTitle", "");
			incomingRequest.put("Report_reportDatawindow", "");
			incomingRequest.put("Report_reportModule", "");
			incomingRequest.put("Report_reportDescription", "");

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
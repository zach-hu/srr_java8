package com.tsa.puridiom.reportqueue.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ReportQueueNextRunGenerate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String frequency = (String)incomingRequest.get("ReportQueue_frequency");
			String nextRun   = (String)incomingRequest.get("ReportQueue_nextRun");
			String startDate = (String)incomingRequest.get("ReportQueue_startDate");

			nextRun = startDate;
			result = nextRun;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
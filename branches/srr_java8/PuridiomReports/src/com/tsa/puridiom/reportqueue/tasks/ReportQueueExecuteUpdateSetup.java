package com.tsa.puridiom.reportqueue.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ReportQueueExecuteUpdateSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReportQueue reportQueue = (ReportQueue)incomingRequest.get("reportQueue");
			if (reportQueue == null)
			{
				throw new Exception ("ReportQueue was not found.");
			}

			Boolean failed = (Boolean)incomingRequest.get("failed");
			if(failed == null)
			{
				failed = Boolean.FALSE;
			}

			if(failed.booleanValue())
			{
				reportQueue.setStatus("99");
			}
			else
			{
				reportQueue.setStatus("10");
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
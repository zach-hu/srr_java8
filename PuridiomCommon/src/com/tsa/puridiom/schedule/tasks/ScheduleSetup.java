package com.tsa.puridiom.schedule.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ScheduleSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("Schedule_scheduleType", "");
			incomingRequest.put("Schedule_documentType", "");
			incomingRequest.put("Schedule_icHeader", "0");
			incomingRequest.put("Schedule_lineNumber", "0");
			incomingRequest.put("Schedule_description", "");
			incomingRequest.put("Schedule_scheduleDate", "2003-11-18");
			incomingRequest.put("Schedule_completionDate", "2003-11-18");
			incomingRequest.put("Schedule_status", "");
			incomingRequest.put("Schedule_revisedDate", "2003-11-18");
			incomingRequest.put("Schedule_scheduleAmount", "0");

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
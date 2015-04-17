package com.tsa.puridiom.invtask.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InvTaskSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InvTask_taskId", "");
			incomingRequest.put("InvTask_refNo", "0");
			incomingRequest.put("InvTask_stageId", "");
			incomingRequest.put("InvTask_taskType", "");
			incomingRequest.put("InvTask_description", "");
			incomingRequest.put("InvTask_notes", "");
			incomingRequest.put("InvTask_dateEntered", Dates.today("", ""));
			incomingRequest.put("InvTask_dateExpires", Dates.today("", ""));
			incomingRequest.put("InvTask_owner", "");
			incomingRequest.put("InvTask_status", "");

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
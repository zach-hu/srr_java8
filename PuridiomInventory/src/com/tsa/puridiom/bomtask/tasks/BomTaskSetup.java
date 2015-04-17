package com.tsa.puridiom.bomtask.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class BomTaskSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("BomTask_icTask", "0");
			incomingRequest.put("BomTask_taskId", "");
			incomingRequest.put("BomTask_refNo", "0");
			incomingRequest.put("BomTask_stageId", "");
			incomingRequest.put("BomTask_icRouting", "0");
			incomingRequest.put("BomTask_taskType", "");
			incomingRequest.put("BomTask_description", "");
			incomingRequest.put("BomTask_notes", "");
			incomingRequest.put("BomTask_dateEntered", Dates.today("", ""));
			incomingRequest.put("BomTask_owner", "");

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
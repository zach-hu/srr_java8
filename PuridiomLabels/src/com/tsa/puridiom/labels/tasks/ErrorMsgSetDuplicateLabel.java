package com.tsa.puridiom.labels.tasks;

import com.tsa.puridiom.entity.Labels;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ErrorMsgSetDuplicateLabel extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = "";
			Labels labels = (Labels) incomingRequest.get("labels");

			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}

			message = message + "  A record for this label already exists. " +
				"[" + labels.getModuleAccess() + "]" +
				"[" + labels.getModule() + "-" +
					labels.getLabelCode() + "-" +
					labels.getModuleType() + "]";

			incomingRequest.put("errorMsg", message);

			result = message;
			// Set status to failed so database transactions are not commited
			this.status = Status.FAILED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
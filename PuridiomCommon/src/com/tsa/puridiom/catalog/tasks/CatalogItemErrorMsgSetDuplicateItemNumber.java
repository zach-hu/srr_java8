package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.entity.CatalogItem;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class CatalogItemErrorMsgSetDuplicateItemNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = "";

			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}

			message = message + "A record for this Item Number already exists.";

			incomingRequest.put("errorMsg", message);
			incomingRequest.put("newItem", "true");

			result = message;
			this.status = Status.COMPLETED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
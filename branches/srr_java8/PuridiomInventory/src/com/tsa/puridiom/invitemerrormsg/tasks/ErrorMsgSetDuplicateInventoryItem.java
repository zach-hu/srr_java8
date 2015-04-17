package com.tsa.puridiom.invitemerrormsg.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ErrorMsgSetDuplicateInventoryItem extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String message = "This Inventory Item already exists.  Please enter a new Inventory Item.";

			incomingRequest.put("errorMsg", message);
			incomingRequest.put("itemAction", "CREATE");

			result = message;
			this.status = Status.SUCCEEDED;
		} catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}

		return result;
	}
}
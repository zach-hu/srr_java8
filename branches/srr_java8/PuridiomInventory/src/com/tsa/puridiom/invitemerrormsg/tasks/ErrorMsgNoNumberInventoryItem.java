package com.tsa.puridiom.invitemerrormsg.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.properties.DictionaryManager;

public class ErrorMsgNoNumberInventoryItem extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;


		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			String language = (String) incomingRequest.get("language");
			String message = "Please enter a valid " + DictionaryManager.getLabelsInstance(organizationId, language).getLabel(organizationId, "inv-itemNumber", "Item/Part #");

			incomingRequest.put("errorMsg", message);
			incomingRequest.put("itemAction", "CREATE");

			result = message;
			this.status = Status.FAILED;
		} catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}

		return result;
	}
}
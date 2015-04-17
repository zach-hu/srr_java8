/**
 *
 */
package com.tsa.puridiom.usererrormsg.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny Zapana
 */
public class ErrorMsgSetOrganizationEmpty extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String message = "";

			if (incomingRequest.containsKey("errorMsg"))
			{
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}

			message = message + "  Organization ID is empty.";

			incomingRequest.put("errorMsg", message);

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

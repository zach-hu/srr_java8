/**
 * 
 */
package com.tsa.puridiom.countryerrormsg.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Johnny
 */
public class ErrorMsgSetInvalidCountry extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			String message = "";

			if (incomingRequest.containsKey("errorMsg"))
			{
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}

			message += "  " + DictionaryManager.getLabel(organizationId, "user-locale", "Location Code") + " is invalid.  Your information has not been saved.";

			result = message;

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "ErrorMsgSetInvalidCountry error " + e.getMessage());

			throw e;
		}

		return result;
	}

}

package com.tsa.puridiom.usererrormsg.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ErrorMsgSetAccountLocked extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String	organizationId = (String) incomingRequest.get("organizationId");
			String message = "";

			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}

			String	helpDeskEmail = PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "HelpDeskEmail", "");

			if (HiltonUtility.isEmpty(helpDeskEmail)) {
			    message = message + "  \n Your account is currently locked.  Please contact your system administrator.";
			} else {
			    message = message + "  \n Your account is currently locked.  Please contact the Puridiom Help Desk at " + helpDeskEmail;
			}

			incomingRequest.put("errorMsg", message);
			incomingRequest.put("attempts", "0");

			result = message;
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
package com.tsa.puridiom.usererrormsg.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class WarningMsgSetPasswordExpiring extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
		    Integer	warningRemainingDays = (Integer) incomingRequest.get("warningRemainingDays");
			String message = (String) incomingRequest.get("errorMsg");

			String dayText = ((warningRemainingDays.compareTo(new Integer(1)) == 0)) ? " day." : " days.";
			message = Utility.ckNull(message) + "  Your password will expire in " + warningRemainingDays + dayText + " You can enter a new password now.";

			incomingRequest.put("resetPassword", "true");
			incomingRequest.put("warningMsg", message);
			
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
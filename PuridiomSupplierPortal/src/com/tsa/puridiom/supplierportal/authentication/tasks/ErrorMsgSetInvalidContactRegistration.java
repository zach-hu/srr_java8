package com.tsa.puridiom.supplierportal.authentication.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ErrorMsgSetInvalidContactRegistration extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String errorContactFlag = HiltonUtility.ckNull((String)incomingRequest.get("errorContactFlag"));
			String errorContactMsg = HiltonUtility.ckNull((String)incomingRequest.get("errorContactMsg"));

			String message = "";

			if (errorContactFlag.equalsIgnoreCase("Y"))
			{
				message = errorContactMsg;
			}

			incomingRequest.put("errorMsg", message);
			incomingRequest.put("organizationId", "");

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

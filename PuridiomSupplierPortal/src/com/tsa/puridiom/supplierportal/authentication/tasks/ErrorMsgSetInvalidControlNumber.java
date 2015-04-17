package com.tsa.puridiom.supplierportal.authentication.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ErrorMsgSetInvalidControlNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = HiltonUtility.ckNull((String)incomingRequest.get("errorMsgReturn"));

			incomingRequest.put("errorMsgReturnFlag", "Y");
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
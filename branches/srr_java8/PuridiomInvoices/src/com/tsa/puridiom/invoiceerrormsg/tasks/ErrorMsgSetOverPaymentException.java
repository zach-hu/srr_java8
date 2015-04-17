package com.tsa.puridiom.invoiceerrormsg.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;

public class ErrorMsgSetOverPaymentException extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = HiltonUtility.ckNull((String) incomingRequest.get("errorMsg"));
			List exceptionLineList = (List) incomingRequest.get("exceptionLineList");

			if (exceptionLineList != null)
			{
				if (message.length() > 0)
				{
					message = message + "<BR>";
				}

				message = message + "This invoice has created an over payment exception!  The total amount invoiced exceeds the order total.";
			}

			incomingRequest.put("errorMsg", message);

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
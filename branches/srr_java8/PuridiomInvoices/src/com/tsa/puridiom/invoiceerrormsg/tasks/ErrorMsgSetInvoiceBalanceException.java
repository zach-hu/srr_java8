package com.tsa.puridiom.invoiceerrormsg.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;

public class ErrorMsgSetInvoiceBalanceException extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = "";
			List exceptionLineList = (List) incomingRequest.get("exceptionLineList");

			if (exceptionLineList != null)
			{
				message = "You cannot submit this invoice until your invoice balance is zero.";
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
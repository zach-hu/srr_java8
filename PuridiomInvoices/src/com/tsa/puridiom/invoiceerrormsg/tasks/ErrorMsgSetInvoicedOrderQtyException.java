package com.tsa.puridiom.invoiceerrormsg.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;

public class ErrorMsgSetInvoicedOrderQtyException extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = HiltonUtility.ckNull((String) incomingRequest.get("errorMsg"));
			List exceptionLineList = (List) incomingRequest.get("exceptionLineList");
			List varianceList = (List) incomingRequest.get("varianceList");

			if (exceptionLineList != null)
			{
				if (message.length() > 0)
				{
					message = message + "<BR>";
				}

				for (int i = 0; i < exceptionLineList.size(); i++)
				{
					String variance = "";
					if (varianceList != null)
					{
						variance = HiltonUtility.ckNull((String) varianceList.get(i));
					}
					message = message + "Line Item #" + exceptionLineList.get(i) + " has created a quantity exception!  Invoiced Quantity not within " + variance + "% Tolerance.";
				}
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
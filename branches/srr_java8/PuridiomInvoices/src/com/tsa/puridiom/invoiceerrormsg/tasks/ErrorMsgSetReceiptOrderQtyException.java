package com.tsa.puridiom.invoiceerrormsg.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;

public class ErrorMsgSetReceiptOrderQtyException extends Task
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

				if (exceptionLineList.size() > 1)
	            {
	            	message = message + "Line Items #";
	            	for (int i = 0; i < exceptionLineList.size(); i++)
	            	{
	            		if (i == exceptionLineList.size() - 1)
	            		{
	            			message = message + " and " + exceptionLineList.get(i) + " have created a quantity exception!";
	            		}
	            		else if (i > 0)
	            		{
	            			message = message + ", " + exceptionLineList.get(i);
	            		}
	            		else
	            		{
	            			message = message + exceptionLineList.get(i);
	            		}
	            	}
	            }
	            else if (exceptionLineList.size() == 1)
	            {
	            	message = "Line Item #" + exceptionLineList.get(0) + " has created a quantity exception!";
	            }
				message = message + "  Received Quantity is greater than Ordered Quantity.";
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
package com.tsa.puridiom.vendorerrormsg.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ErrorMsgSetDuplicateVendorName extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = "";

			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}

			message = message + "  A record for a similar Vendor Name already exists.";

			incomingRequest.put("errorMsg", message);
			incomingRequest.put("newVendor", "Y");
			incomingRequest.put("successPage", "/admin/supplier/supplier_info.jsp");

			result = message;
			this.status = Status.COMPLETED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
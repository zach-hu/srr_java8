package com.tsa.puridiom.vendorerrormsg.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ErrorMsgSetDuplicateVendorEin extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = "";
			String oid = (String) incomingRequest.get("organizationId");

			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}
			if (oid.equalsIgnoreCase("hoy08p"))
			{
				message = message + "  A record for a similar Tax ID Number already exists.";
			}
			else
			{
				message = message + "  A record for a similar Vendor EIN already exists.";
			}
			incomingRequest.put("errorMsg", message);
			incomingRequest.put("successPage", "/admin/supplier/supplier_info.jsp");

			if(incomingRequest.containsKey("checkVendorEin")){
				incomingRequest.put("newVendor", "F");
			}
			else {
				incomingRequest.put("newVendor", "Y");
			}

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
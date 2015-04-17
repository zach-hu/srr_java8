package com.tsa.puridiom.vendorerrormsg.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ErrorMsgSetQualifiedVendorAlreadyExists extends Task
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
			
			String	vendorId = (String) incomingRequest.get("vendorId");
			
			message = message + "  This vendor [" + HiltonUtility.ckNull(vendorId) + "] is already listed as a qualified vendor.";
			
			incomingRequest.put("errorMsg", message);
			incomingRequest.put("newVendor", "Y");
			
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
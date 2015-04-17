package com.tsa.puridiom.approvals.errormsg.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ErrorMsgSetNoMinimumBackupApprovalAmount extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = "";
			String	oid = (String) incomingRequest.get("organizationId");

			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}

			String minimumBackupApprovalAmount = PropertiesManager.getInstance(oid).getProperty("APPROVALS", "MINIMUMBACKUPAPPROVALAMOUNT", "0") ;
			message = message + "  Backup approver doesn't have mininum Backup Approval Amount (" + minimumBackupApprovalAmount + ").";

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
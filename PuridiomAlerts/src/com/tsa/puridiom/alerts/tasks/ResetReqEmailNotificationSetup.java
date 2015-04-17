package com.tsa.puridiom.alerts.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ResetReqEmailNotificationSetup extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			ApprovalLog approvalLog = (ApprovalLog)incomingRequest.get("approvalLog");
			incomingRequest.put("forwardedTo", approvalLog.getUserId());
            incomingRequest.put("approverType", approvalLog.getApproverType());
			String fromEmail = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getProperty("MAILEVENTS", "AdminEmailAddr", "");
			//String fromEmail = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), (String)incomingRequest.get("userId")).getMailId();
			incomingRequest.put("SendQueue_sendfrom", fromEmail);
			incomingRequest.put("duplicateNotification", "Y");

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}

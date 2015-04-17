/**
 * 
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Johnny
 *
 */
public class ApproverRetrieveFromApprovalLog extends Task
{

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			ApprovalLog lastApprovalLog = (ApprovalLog) incomingRequest.get("lastApprovalLog");
			
			result = UserManager.getInstance().getUser(organizationId, lastApprovalLog.getUserId());
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}

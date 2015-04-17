package com.tsa.puridiom.invoice.approvals.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class InvoiceApprovalCheckUser extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List<ApprovalLog> routingList = (List)incomingRequest.get("routingList");
			String userId = (String)incomingRequest.get("userId");

			if (routingList == null) {
				throw new Exception("routingList can't be empty");
			}

			for (ApprovalLog approvalLog : routingList)
			{
				if (approvalLog.getComp_id().getUserId().equalsIgnoreCase(userId) && approvalLog.getApproved().equalsIgnoreCase("Y"))
				{
					result = approvalLog;
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "There was a problem checking if the current user already approved Invoice");
			e.printStackTrace();
		}

		return result;
	}
}

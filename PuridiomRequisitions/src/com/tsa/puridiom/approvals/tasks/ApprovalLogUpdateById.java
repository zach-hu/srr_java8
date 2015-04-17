package com.tsa.puridiom.approvals.tasks;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ApprovalLogUpdateById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    ApprovalLog approvalLog = (ApprovalLog) incomingRequest.get("approvalLog");
			if (approvalLog == null)
			{
                this.setStatus(Status.FAILED);
				throw new Exception ("ApprovalLog was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(approvalLog);

			result = approvalLog;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
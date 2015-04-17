package com.tsa.puridiom.approvals.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class HealMultipleCurrentApprover extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object ret = null;

		try
		{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String organizationId = (String)incomingRequest.get("organizationId");
			List routingList = (List)incomingRequest.get("healRoutingList");
			if(routingList != null)
			{
				int listSize = routingList.size();
				for(int i = 0 ; i < listSize; i++)
				{
					ApprovalLog appLog = (ApprovalLog)routingList.get(i);
					
					if(appLog.getApproved().equalsIgnoreCase("A") && appLog.getDateApproved()!= null)
					{
						appLog.setApproved("Y");
						Map newIncomingRequest = new HashMap();
						newIncomingRequest.put("organizationId", organizationId);
						newIncomingRequest.put("userId", incomingRequest.get("userId"));
						newIncomingRequest.put("dbsession", dbs);
						newIncomingRequest.put("approvalLog", appLog);
						ApprovalLogUpdate task = new ApprovalLogUpdate();
						task.executeTask(newIncomingRequest);
					}
				}
			}
		    ret = routingList;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("An error ocurred while healing appLog.", e);
		}
		return ret ;
	}
}
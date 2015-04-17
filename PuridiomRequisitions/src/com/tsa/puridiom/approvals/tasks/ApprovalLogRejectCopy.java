/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RejectLog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
/**
 * @author Administrator
 */
public class ApprovalLogRejectCopy extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;

		try
		{
			List routingList =(List)incomingRequest.get("routingList");
			List routingListCopy = new ArrayList();
			for(int i = 0; i < routingList.size(); i++)
			{
				ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);
				RejectLog rejectLog = new RejectLog(approvalLog);
				UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		        rejectLog.setIcReject(new BigDecimal(ukg.getUniqueKey().toString()));

				routingListCopy.add(rejectLog);
				if(approvalLog.getApproved().equalsIgnoreCase("R"))
				{
					i = routingList.size();
				}
			}
			ret = routingListCopy;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Routing List could not be copied.", e);
		}
		return ret;
	}
}

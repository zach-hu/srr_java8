package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.approvals.tasks.ApprovalLogRetrieveBy;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequisitionHeaderUpdateAppBy extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");

			if (requisitionHeader != null && requisitionHeader.getApproved().equalsIgnoreCase("N") &&
				requisitionHeader.getStatus().compareTo(DocumentStatus.REQ_APPROVING) == 0)
			{
				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("userDateFormat", incomingRequest.get("userDateFormat"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("ApprovalLog_icHeader", requisitionHeader.getIcReqHeader().toString());
				updateParameters.put("ApprovalLog_approved", "A");

				ApprovalLogRetrieveBy approvalLogRetrieveBy = new ApprovalLogRetrieveBy();
				List approvalLogList = (List)approvalLogRetrieveBy.executeTask(updateParameters);

				if (approvalLogList != null && approvalLogList.size() > 0)
				{
					ApprovalLog approvalLog = (ApprovalLog)approvalLogList.get(0);
					if (approvalLog != null)
					{
						requisitionHeader.setAppBy(approvalLog.getUserId());
						dbs.update(requisitionHeader);
					}
				}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) 
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}
}

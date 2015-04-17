/*
 * @author ebsGroup mcvz
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class ApprovalAbleCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result;
		String approvalAbleCheck = "N";
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icReqHeaderString = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
			if (Utility.isEmpty(icReqHeaderString))
			{
				throw new Exception("RequisitionHeader_icReqHeader cannot be empty. ApprovalAbleCheck Fail.");
			}
			String user = (String)incomingRequest.get("userId") ;
			if (Utility.isEmpty(user))
			{
				throw new Exception("userId cannot be empty. ApprovalAbleCheck Fail.");
			}
			String queryString = "from RequisitionHeader as RequisitionHeader, ApprovalLog as ApprovalLog, WHERE RequisitionHeader.icReqHeader = ? AND ApprovalLog.id.icHeader = ? AND RequisitionHeader.status = ? AND " +
					"ApprovalLog.approved = 'A' AND ApprovalLog.callForward=?";
			Object [] args = {icReqHeaderString, icReqHeaderString, DocumentStatus.REQ_APPROVING, user};
			Type [] types = {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING};
			List resultList = dbs.query(queryString, args, types);

			if (resultList != null && resultList.size() > 0)
			{
				approvalAbleCheck = "Y";
			}
			result = approvalAbleCheck;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("ApprovalAbleCheck failed with: " + e.getMessage(), e);
		}
		return result ;
    }
}

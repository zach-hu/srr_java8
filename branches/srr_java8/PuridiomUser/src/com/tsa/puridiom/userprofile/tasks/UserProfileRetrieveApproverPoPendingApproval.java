package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.util.List;
import java.util.Map;

public class UserProfileRetrieveApproverPoPendingApproval extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String approver = "Y";
			String status = "03";
			String poNumber = "N/A";
			String approved = "A";

			String queryString = "select UserProfile.userId, count(PoHeader.icPoHeader) " +
				"from UserProfile as UserProfile, PoHeader as PoHeader, ApprovalLog as ApprovalLog " +
				"where UserProfile.approver = ? and UserProfile.status <> ? and PoHeader.poNumber <> ? " +
				"and ApprovalLog.id.icHeader = PoHeader.icPoHeader and PoHeader.status = ? and ApprovalLog.approved = ? " +
				"and (ApprovalLog.callForward = UserProfile.userId or ApprovalLog.backupApprover = UserProfile.userId) " +
				"group by UserProfile.userId order by UserProfile.userId";
			List resultList = dbs.query(queryString,
				new Object[] {approver, status, poNumber, DocumentStatus.PO_APPROVING, approved},
				new Type[] {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING});

			incomingRequest.put("UserPreference_property", "POPENDINGAPPROVALCNT");
			result = resultList;

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}

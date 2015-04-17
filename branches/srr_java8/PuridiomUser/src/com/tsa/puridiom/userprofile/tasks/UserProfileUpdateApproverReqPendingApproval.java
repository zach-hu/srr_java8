package com.tsa.puridiom.userprofile.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class UserProfileUpdateApproverReqPendingApproval extends Task
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
			String requisitionNumber = "N/A";
			String approved = "A";
			String approverType = "P";
			String property = "REQPENDINGAPPROVALCNT";

			String sqlString = "UPDATE User_Preference SET " +
					"VALUE = (select count(RequisitionHeader.ic_Req_Header) " +
					"FROM User_Profile as UserProfile, Requisition_Header as RequisitionHeader, Approval_Log as ApprovalLog " +
					"WHERE UserProfile.approver = ? " +
					"AND UserProfile.status <> ? " +
					"AND RequisitionHeader.requisition_Number <> ? " +
					"AND ApprovalLog.ic_Header = RequisitionHeader.ic_Req_Header " +
					"AND (RequisitionHeader.status = ? or RequisitionHeader.status = ?) " +
					"AND ApprovalLog.approved = ? " +
					"AND (ApprovalLog.call_Forward = UserProfile.user_Id or ApprovalLog.backup_Approver = UserProfile.user_Id or " +
						"(ApprovalLog.approver_Type = ? AND ApprovalLog.user_Id " +
							"IN (SELECT AppPooluser.poolid FROM App_Pooluser as AppPoolUser WHERE AppPoolUser.user_Id = UserProfile.user_Id))) " +
					"AND User_Preference.user_Id = UserProfile.user_Id " +
					"GROUP BY UserProfile.user_Id) " +
					"WHERE User_Preference.property = ?";


			Object [] args = {approver, status, requisitionNumber, DocumentStatus.REQ_APPROVING, DocumentStatus.REQ_PLANNING_APPROVING,approved, approverType, property};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

			dbs.sqlUpdate(sqlString, args, types);

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

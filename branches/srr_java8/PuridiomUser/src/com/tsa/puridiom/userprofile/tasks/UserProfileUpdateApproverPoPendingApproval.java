package com.tsa.puridiom.userprofile.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class UserProfileUpdateApproverPoPendingApproval extends Task
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
			String property = "POPENDINGAPPROVALCNT";

			String sqlString = "UPDATE User_Preference SET VALUE = (select count(PoHeader.ic_Po_Header) " +
				"from User_Profile as UserProfile, Po_Header as PoHeader, Approval_Log as ApprovalLog " +
				"where UserProfile.approver = ? and UserProfile.status <> ? and PoHeader.po_Number <> ? " +
				"and ApprovalLog.ic_Header = PoHeader.ic_Po_Header and (PoHeader.status = ? or PoHeader.status = ?) and ApprovalLog.approved = ? " +
				"and User_Preference.user_Id = UserProfile.user_Id " +
				"and (ApprovalLog.call_Forward = UserProfile.user_Id or ApprovalLog.backup_Approver = UserProfile.user_Id) " +
				"group by UserProfile.user_Id) " +
				"WHERE User_Preference.property = ?";
			
			Object [] args = {approver, status, poNumber, DocumentStatus.PO_APPROVING, DocumentStatus.CT_APPROVING, approved, property};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
			
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

package com.tsa.puridiom.userprofile.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class UserProfileUpdateRecInspection extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String status = "03";
			String recNumber = "N/A";
			String recStatus = DocumentStatus.RCV_STEP_1;
			String property = "RECININSPECTIONCNT";

			String sqlString = "UPDATE User_Preference SET VALUE = (select count(ReceiptHeader.ic_Rec_Header) " +
				"from User_Profile as UserProfile, Receipt_Header as ReceiptHeader " +
				"where UserProfile.status <> ? and ReceiptHeader.receipt_Number <> ? " +
				"and ReceiptHeader.receipt_Number is not null and ReceiptHeader.receipt_Number <> 'null' " +
				"and ReceiptHeader.forward_To is not null and ReceiptHeader.forward_To <> 'null' " +
				"and ReceiptHeader.ic_Rec_Header in (Select distinct ReceiptLine.ic_Rec_Header from Receipt_Line as ReceiptLine " +
				"where ReceiptLine.status = ? and ReceiptLine.inspector_Assigned = UserProfile.user_Id) " +
				"and User_Preference.user_Id = UserProfile.user_Id " +
				"group by UserProfile.user_Id) " +
				"WHERE User_Preference.property = ?";
			
			Object [] args = {status, recNumber, recStatus, property};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
			
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

package com.tsa.puridiom.userprofile.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class UserProfileUpdateBuyerReqPriced extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String status = "03";
			String requisitionNumber = "N/A";
			String property = "REQPRICEDCNT";

			String sqlString = "UPDATE User_Preference SET VALUE = (select count(RequisitionHeader.ic_Req_Header) " +
				"from User_Profile as UserProfile, Requisition_Header as RequisitionHeader " +
				"where UserProfile.status <> ? and RequisitionHeader.requisition_Number <> ? " +
				"and RequisitionHeader.requisition_Type = 'N' " +
				"and RequisitionHeader.status = ? " +
				"and RequisitionHeader.assigned_Buyer = UserProfile.user_Id " +
				"and User_Preference.user_Id = UserProfile.user_Id " +
				"group by UserProfile.user_Id) " +
				"WHERE User_Preference.property = ?";
			
			Object [] args = {status, requisitionNumber, DocumentStatus.REQ_FORWARDED, property};
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

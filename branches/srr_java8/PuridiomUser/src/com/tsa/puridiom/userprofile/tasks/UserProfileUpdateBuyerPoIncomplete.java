package com.tsa.puridiom.userprofile.tasks;

import java.sql.Types;
import java.util.Calendar;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class UserProfileUpdateBuyerPoIncomplete extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String buyer = "Y";
			String status = "03";
			String poNumber = "N/A";
			String property = "POINCOMPLETECNT";

			String sqlString = "UPDATE User_Preference SET VALUE = (select count(PoHeader.ic_Po_Header) " +
				"from User_Profile as UserProfile, Po_Header as PoHeader " +
				"where UserProfile.buyer = ? and UserProfile.status <> ? and PoHeader.po_Number <> ? " +
				"and PoHeader.status = ? and User_Preference.user_Id = UserProfile.user_Id and (PoHeader.owner = UserProfile.user_Id or PoHeader.buyer_Code = UserProfile.user_Id) " +
				"group by UserProfile.user_Id) " +
				"WHERE User_Preference.property = ?";
			
			Object [] args = {buyer, status, poNumber, DocumentStatus.PO_INPROGRESS, property};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
			
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

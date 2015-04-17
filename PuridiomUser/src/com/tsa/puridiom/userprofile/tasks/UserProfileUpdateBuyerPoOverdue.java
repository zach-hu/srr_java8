package com.tsa.puridiom.userprofile.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class UserProfileUpdateBuyerPoOverdue extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String organizationId = (String)incomingRequest.get("organizationId");

			String buyer = "Y";
			String status = "03";
			String poNumber = "N/A";
			String property = "POOVERDUECNT";
			java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());

			String sqlString = "UPDATE User_Preference SET VALUE = (select count(PoHeader.ic_Po_Header) " +
				"from User_Profile as UserProfile, Po_Header as PoHeader " +
				"where UserProfile.buyer = ? and UserProfile.status <> ? and PoHeader.po_Number <> ? " +
				"and PoHeader.status > ? and PoHeader.status < ? and PoHeader.required_Date < ? ";
			if (PropertiesManager.getInstance(organizationId).getProperty("PO OPTIONS", "FILTER DIFFERENT AMENDED","N").equalsIgnoreCase("Y")) {
				sqlString = sqlString + "and PoHeader.status <> ? ";
			}
			sqlString = sqlString + "and User_Preference.user_Id = UserProfile.user_Id and (PoHeader.owner = UserProfile.user_Id or PoHeader.buyer_Code = UserProfile.user_Id) " +
				"group by UserProfile.user_Id) " +
				"WHERE User_Preference.property = ?";

			if (PropertiesManager.getInstance(organizationId).getProperty("PO OPTIONS", "FILTER DIFFERENT AMENDED","N").equalsIgnoreCase("Y"))
			{
				Object [] args = {buyer, status, poNumber, DocumentStatus.PO_APPROVING, DocumentStatus.RCV_RECEIVED, currentDate, DocumentStatus.PO_AMENDED, property};
				Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR, Types.VARCHAR};
				
				dbs.sqlUpdate(sqlString, args, types);
			}
			else
			{
				Object [] args = {buyer, status, poNumber, DocumentStatus.PO_APPROVING, DocumentStatus.RCV_RECEIVED, currentDate, property};
				Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR};
				
				dbs.sqlUpdate(sqlString, args, types);
			}
			
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

package com.tsa.puridiom.userprofile.tasks;

import java.sql.Types;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class UserProfileUpdateRecInventory extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String status = "03";
			String recStatus = DocumentStatus.RCV_PARTIALLYRECEIVED;
			String property = "RECPENDINGONINVENTORYCNT";

			String sqlString = "UPDATE User_Preference SET VALUE = (select count(ReceiptLine.ic_Rec_Header) " +
				"from Receipt_Line as ReceiptLine, Po_Header as PoHeader, Po_Line as PoLine, User_Profile as UserProfile " +
				"where UserProfile.status <> ? " +
				"and ReceiptLine.ic_Po_Header = PoHeader.ic_Po_Header and PoHeader.ic_Po_Header = PoLine.ic_Po_Header " +
				"and ReceiptLine.inventory_Status = ? " +
				"and User_Preference.user_Id = UserProfile.user_Id " +
				"group by UserProfile.user_Id) " +
				"WHERE User_Preference.property = ?";

			Object [] args = {status, recStatus, property};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
			
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

package com.tsa.puridiom.inscategorylevel.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class InsCategoryLevelRetrieveAll extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			String userTimeZone = (String) incomingRequest.get("userTimeZone");
			String userDateFormat = (String) incomingRequest.get("userDateFormat");
			if (Utility.isEmpty(userDateFormat)) {
				userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
			}
			String today = Dates.today(userDateFormat, userTimeZone);

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String queryString = "SELECT InsCategoryLevel.iclLevel FROM InsCategoryLevel as InsCategoryLevel " +
				"WHERE (InsCategoryLevel.status = '02' OR (InsCategoryLevel.status = '01' AND InsCategoryLevel.dateExpires >= ? ))";
			result = dbs.query(queryString, new Object[] {Dates.getSqlDate(today, userDateFormat)}, new Type[] {Hibernate.DATE});

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "InsCategoryLevelRetrieveAll: " + e.getMessage());
		}
		return result;
	}
}

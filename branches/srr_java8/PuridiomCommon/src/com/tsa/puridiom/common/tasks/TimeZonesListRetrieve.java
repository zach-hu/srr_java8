package com.tsa.puridiom.common.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class TimeZonesListRetrieve extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String queryString = "select stdtable.id.tableKey from StdTable as stdtable " +
				"where stdtable.id.tableType = 'TMZN' and stdtable.status = '02' or (stdtable.status = '01' and stdtable.dateExpires >= ?) order by stdtable.id.tableKey ASC";

			List timeZonesList = dbs.query(queryString, new Object[] {Dates.getDate(Dates.today("", (String) incomingRequest.get("userTimeZone")))}, new Type[] {Hibernate.DATE});

			Map timeZonesMap = new HashMap();
			if (timeZonesList != null && timeZonesList.size() > 0)
			{
				for (int i = 0; i < timeZonesList.size(); i++)
				{
					String timeZone = timeZonesList.get(i).toString();
					timeZonesMap.put(timeZone, HiltonUtility.getFormattedTimeZone(timeZone) + " - " + timeZone);
				}
			}
			result = timeZonesMap;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "TimeZonesListRetrieve failed: " + e.getMessage());
		}

		return result;
	}
}

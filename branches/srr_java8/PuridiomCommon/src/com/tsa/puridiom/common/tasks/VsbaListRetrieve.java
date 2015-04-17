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

public class VsbaListRetrieve extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			String queryString = "select stdtable.id.tableKey, stdtable.description from StdTable as stdtable " +
				"where stdtable.id.tableType = 'VSBA' and stdtable.status = '02' or (stdtable.status = '01' and stdtable.dateExpires >= ?) order by stdtable.id.tableKey ASC";

			List vsbaList = dbs.query(queryString, new Object[] {Dates.getDate(Dates.today("", (String) incomingRequest.get("userTimeZone")))}, new Type[] {Hibernate.DATE});

			Map vsbaMap = new HashMap();
			if (vsbaList != null && vsbaList.size() > 0)
			{
				for (int i = 0; i < vsbaList.size(); i++)
				{
					String tableKey = "";
					String description = "";
					Object obj = vsbaList.get(i);
					if (obj instanceof Object[]) {
						tableKey = ((Object[])obj)[0].toString();
						description = ((Object[])obj)[1].toString();;
					}

					if (!HiltonUtility.isEmpty(tableKey)) {
						vsbaMap.put(tableKey, description);
					}
				}
			}
			result = vsbaMap;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "VsbaListRetrieve failed: " + e.getMessage());
		}

		return result;
	}
}

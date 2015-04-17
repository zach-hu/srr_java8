/**
 * Created on May 24, 2004
 * @author renzo
 * project: HiltonReports
 * com.tsa.puridiom.reports.itemusage.taks.GetItemUsage.java
 *
 */
package com.tsa.puridiom.reports.itemusage.tasks;

import java.util.Calendar;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * GetItemUsage 
 */
public class GetItemUsage extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result;
		try
		{
			Map incomingRequest = (Map)object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			StringBuffer queryString = new StringBuffer("from InvUsage as invUsage where 1=1 ");
			if(incomingRequest.containsKey("InvUsage_itemNumber"))
			{			
				String itemNumber = (String) incomingRequest.get("InvUsage_itemNumber");
				queryString.append(" AND invUsage.id.itemNumber = '" + itemNumber + "' ");
			}
			Calendar cal = Calendar.getInstance();
			
			String year = String.valueOf(cal.get(Calendar.YEAR));
			String month = String.valueOf(cal.get(Calendar.MONTH));
			
			/*if(month.equals("0"))
			{
				queryString.append(" AND invUsage.id.usageYear > " + (cal.get(Calendar.YEAR) - 2));
			}
			else
			{*/
				queryString.append(" AND ((invUsage.id.usageYear = " + year + " AND invUsage.id.usageMonth < " + month + ")");
				queryString.append(" OR (invUsage.id.usageYear = " + (cal.get(Calendar.YEAR) - 1) + ")");
				queryString.append(" OR (invUsage.id.usageMonth >= " + month + " AND invUsage.id.usageYear = " + (cal.get(Calendar.YEAR) - 2) + "))");
			//}
			result = dbs.query(queryString.toString()) ;
			incomingRequest.put("date", cal);
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result ;
	}
}

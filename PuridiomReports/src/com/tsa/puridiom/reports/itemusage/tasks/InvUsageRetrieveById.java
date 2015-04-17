package com.tsa.puridiom.reports.itemusage.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvUsageRetrieveById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Object result = null;
		try
		{
			Map incomingRequest = (Map)object;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			StringBuffer queryString = new StringBuffer("from InvUsage as invUsage where 1=1 ");
			
			if(incomingRequest.containsKey("InvUsage_itemNumber"))
			{			
				String itemNumber = (String) incomingRequest.get("InvUsage_itemNumber");
				queryString.append(" AND invUsage.id.itemNumber = '" + itemNumber + "'");
			}
			if(incomingRequest.containsKey("InvUsage_usageYear"))
			{			
				String usageYear = (String) incomingRequest.get("InvUsage_usageYear");
				queryString.append(" AND invUsage.id.usageYear = '" + usageYear + "'");
			}
			if(incomingRequest.containsKey("InvUsage_usageMonth"))
			{			
				String usageMonth = (String) incomingRequest.get("InvUsage_usageMonth");
				queryString.append(" AND invUsage.id.usageMonth = '" + usageMonth + "'");
			}
			
			//result = dbs.query(queryString.toString()) ;
			List resultList = dbs.query(queryString.toString()) ;
			
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			
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
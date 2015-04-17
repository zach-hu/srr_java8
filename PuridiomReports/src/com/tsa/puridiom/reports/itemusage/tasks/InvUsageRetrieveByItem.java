package com.tsa.puridiom.reports.itemusage.tasks;

import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvUsageRetrieveByItem extends Task
{
	public Object  executeTask (Object object) throws Exception
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
				queryString.append(" AND invUsage.id.itemNumber = '" + itemNumber + "'");
			}	
			result = dbs.query(queryString.toString()) ;
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
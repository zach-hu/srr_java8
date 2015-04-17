package com.tsa.puridiom.recentreqitem.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class RecentReqItemAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RecentReqItem recentReqItem = (RecentReqItem)incomingRequest.get("recentReqItem");
			if (recentReqItem == null)
			{
				throw new Exception ("RecentReqItem was not found.");
			}
			
			if (recentReqItem.getRequestCount().compareTo(new BigDecimal(0)) <= 0)
			{
			    recentReqItem.setRequestCount(new BigDecimal(1));
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(recentReqItem);
		
			result = recentReqItem;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
package com.tsa.puridiom.recentreqitem.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RecentReqItemUpdate extends Task
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
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(recentReqItem);
		
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
package com.tsa.puridiom.recentorderitem.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RecentOrderItemUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RecentOrderItem recentOrderItem = (RecentOrderItem)incomingRequest.get("recentOrderItem");
			if (recentOrderItem == null)
			{
				throw new Exception ("RecentOrderItem was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(recentOrderItem);
		
			result = recentOrderItem;
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
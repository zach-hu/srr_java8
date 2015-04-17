package com.tsa.puridiom.recentreceipt.tasks;

import com.tsa.puridiom.entity.RecentReceipt;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RecentReceiptAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RecentReceipt recentReceipt = (RecentReceipt)incomingRequest.get("recentReceipt");
			if (recentReceipt == null)
			{
				throw new Exception ("RecentReceipt was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(recentReceipt);
		
			result = recentReceipt;
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
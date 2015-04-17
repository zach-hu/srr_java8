package com.tsa.puridiom.invbinlochistory.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvBinLocHistoryUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InvBinLocHistory invBinLocHistory = (InvBinLocHistory)incomingRequest.get("invBinLocHistory");
			if (invBinLocHistory == null)
			{
				throw new Exception ("InvBinLocHistory was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(invBinLocHistory);
		
			result = invBinLocHistory;
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
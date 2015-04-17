package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class RfqBidHistoryUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			RfqBidHistory rfqBidHistory = (RfqBidHistory)incomingRequest.get("rfqBidHistory");
			if (rfqBidHistory == null)
			{
				throw new Exception ("RfqBidHistory was not found.");
			}
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(rfqBidHistory);
			result = rfqBidHistory;
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
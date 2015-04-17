package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class RfqBidUpdateById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RfqBid rfqBid = (RfqBid)incomingRequest.get("rfqBid");
			if (rfqBid == null)
			{
				throw new Exception ("RfqBid was not found.");
			}

			String userTimeZone = (String) incomingRequest.get("userTimeZone");

			rfqBid.setLastChgDate(Dates.getSqlDate(Dates.today("", userTimeZone)));

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(rfqBid);

			result = rfqBid;
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
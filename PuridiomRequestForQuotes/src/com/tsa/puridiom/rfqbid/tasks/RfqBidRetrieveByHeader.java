package com.tsa.puridiom.rfqbid.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqBidRetrieveByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("RfqBid_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal(icRfqHeaderString);
			StringBuffer queryString = new StringBuffer("from RfqBid as rfqbid where rfqbid.id.icRfqHeader = '" + icRfqHeader + "'");
	
			List rfqBidList = dbs.query(queryString.toString()) ;
			
			result = rfqBidList;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
		}		
		return result ;
	}
}
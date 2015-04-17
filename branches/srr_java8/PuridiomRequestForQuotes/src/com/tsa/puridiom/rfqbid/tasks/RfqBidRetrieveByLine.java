package com.tsa.puridiom.rfqbid.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqBidRetrieveByLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqLineString = (String) incomingRequest.get("RfqBid_icRfqLine");
			BigDecimal icRfqLine = new BigDecimal(icRfqLineString);
			StringBuffer queryString = new StringBuffer("from RfqBid as rfqbid where rfqbid.id.icRfqLine = '" + icRfqLine + "'");
			
			if (this.applicationName.equalsIgnoreCase("BIDBOARD")) {
				queryString.append(" order by rfqbid.unitPrice ASC");
			} else {
			    queryString.append(" order by rfqbid.id.vendorId ASC");
			}
	
			List rfqBidList = dbs.query(queryString.toString()) ;
			
			if (rfqBidList == null) {
			    Log.debug(this, "RfqBidRetrieveByLine[" + icRfqLineString + "] returned null");
			} else {
			    Log.debug(this, "RfqBidRetrieveByLine[" + icRfqLineString + "] returned " + rfqBidList.size() + " records.");
			}
			result = rfqBidList;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) 
		{
		    Log.error(this, e.getMessage());
			this.setStatus(Status.FAILED);
		}		
		return result ;
	}
}
package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqBidHistoryRetrieveByLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("RfqBidHistory_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal(icRfqHeaderString);
			String icRfqLineString = (String) incomingRequest.get("RfqBidHistory_icRfqLine");
			BigDecimal icRfqLine = new BigDecimal(icRfqLineString);
			String vendorId = (String) incomingRequest.get("RfqBidHistory_vendorId");
			StringBuffer queryString = new StringBuffer("from RfqBidHistory RfqBidHistory where RfqBidHistory.id.icRfqHeader = '" + icRfqHeader + "' and RfqBidHistory.id.icRfqLine = '" + icRfqLine + "' and RfqBidHistory.id.vendorId = '" + vendorId + "' and RfqBidHistory.id.bidSequence > 0");
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
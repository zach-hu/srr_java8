package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RfqBidHistoryListRetrieveByHeaderVendor extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icRfqHeaderString = (String) incomingRequest.get("RfqBid_icRfqHeader");
			String vendorId = "";
			Object vendorIdObj = incomingRequest.get("RfqBid_vendorId");
			if (vendorIdObj instanceof String[]) {
				String arrayVendorId[] = (String[]) vendorIdObj;
				vendorId = arrayVendorId[0];
			}
			else {
				vendorId = (String) incomingRequest.get("RfqBid_vendorId");
			}
			BigDecimal icRfqHeader = new BigDecimal(icRfqHeaderString);
			StringBuffer queryString = new StringBuffer("from RfqBidHistory RfqBidHistory where RfqBidHistory.id.icRfqHeader = '" + icRfqHeader + "' and RfqBidHistory.id.vendorId = '" + vendorId + "' and RfqBidHistory.id.bidSequence = ( Select MAX(RfqBidHistory.id.bidSequence) from RfqBidHistory RfqBidHistory Where RfqBidHistory.id.icRfqHeader = '" + icRfqHeader + "' and RfqBidHistory.id.vendorId = '" + vendorId + "')");

			List rfqBidHistoryList = dbs.query(queryString.toString()) ;

			result = rfqBidHistoryList;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}
		return result ;
	}
}
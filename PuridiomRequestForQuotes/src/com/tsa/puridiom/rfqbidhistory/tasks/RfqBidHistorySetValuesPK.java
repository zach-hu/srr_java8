package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class RfqBidHistorySetValuesPK
{
	public void setValues(Map incomingRequest, RfqBidHistory rfqBidHistory ) throws Exception
	{
		try
		{
			String icRfqHeaderString = (String) incomingRequest.get("RfqBid_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
			String icRfqLineString = (String) incomingRequest.get("RfqBid_icRfqLine");
			BigDecimal icRfqLine = new BigDecimal ( icRfqLineString );
			String vendorId = (String ) incomingRequest.get("RfqBid_vendorId");
			BigDecimal bidHistory_sequenceNo = (BigDecimal)incomingRequest.get("bidHistory_sequenceNo");
			RfqBidHistoryPK comp_id = new RfqBidHistoryPK();
			comp_id.setIcRfqHeader(icRfqHeader);
			comp_id.setIcRfqLine(icRfqLine);
			comp_id.setVendorId(vendorId);
			comp_id.setBidSequence(bidHistory_sequenceNo);
			rfqBidHistory.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
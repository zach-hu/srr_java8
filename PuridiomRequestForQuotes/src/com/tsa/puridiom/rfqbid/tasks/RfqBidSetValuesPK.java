package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class RfqBidSetValuesPK
{
	public void setValues(Map incomingRequest, RfqBid rfqbid ) throws Exception
	{
		try
		{
			String icRfqHeaderString = (String) incomingRequest.get("RfqBid_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
			String icRfqLineString = (String) incomingRequest.get("RfqBid_icRfqLine");
			BigDecimal icRfqLine = new BigDecimal ( icRfqLineString );
			String vendorId = (String ) incomingRequest.get("RfqBid_vendorId");
			RfqBidPK comp_id = new RfqBidPK();
			comp_id.setIcRfqHeader(icRfqHeader);
			comp_id.setIcRfqLine(icRfqLine);
			comp_id.setVendorId(vendorId);
			rfqbid.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
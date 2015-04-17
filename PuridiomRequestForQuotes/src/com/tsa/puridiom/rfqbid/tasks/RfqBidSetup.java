package com.tsa.puridiom.rfqbid.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class RfqBidSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("RfqBid_icRfqHeader", "0");
			incomingRequest.put("RfqBid_icRfqLine", "0");
			incomingRequest.put("RfqBid_vendorId", "");
			incomingRequest.put("RfqBid_quantity", "0");
			incomingRequest.put("RfqBid_umCode", "");
			incomingRequest.put("RfqBid_umFactor", "1");
			incomingRequest.put("RfqBid_unitPrice", "0");
			incomingRequest.put("RfqBid_discountSource", "");
			incomingRequest.put("RfqBid_discountAmount", "0");
			incomingRequest.put("RfqBid_discountPercent", "0");
			incomingRequest.put("RfqBid_shippingCharges", "0");
			incomingRequest.put("RfqBid_otherCharges", "0");
			incomingRequest.put("RfqBid_otherDescript", "");
			incomingRequest.put("RfqBid_taxShipping", "");
			incomingRequest.put("RfqBid_taxOther", "");
			incomingRequest.put("RfqBid_commentFlag", "");
			incomingRequest.put("RfqBid_taxPercent", "0");
			incomingRequest.put("RfqBid_taxAmount", "0");
			incomingRequest.put("RfqBid_shippingTaxAmt", "0");
			incomingRequest.put("RfqBid_otherTaxAmount", "0");
			incomingRequest.put("RfqBid_bidCurrency", "");
			incomingRequest.put("RfqBid_lastChgDate", Dates.today("", ""));
			incomingRequest.put("RfqBid_bidCode", "");
			incomingRequest.put("RfqBid_currencyFactor", "1");

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}

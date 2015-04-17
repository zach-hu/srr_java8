package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqBidSetDefaultsFromVendor extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			RfqVendor rfqVendor = (RfqVendor) incomingRequest.get("rfqVendor");
			if (rfqVendor == null)
			{
				rfqVendor = new RfqVendor();
			}
			
			incomingRequest.put("RfqBid_vendorId", rfqVendor.getComp_id().getVendorId());
/*			incomingRequest.put("RfqBid_discountSource", rfqVendor.getDiscountSource());
			incomingRequest.put("RfqBid_discountAmount", String.valueOf(rfqVendor.getDiscountAmount()));
			incomingRequest.put("RfqBid_discountPercent", String.valueOf(rfqVendor.getDiscountPercent()));
			incomingRequest.put("RfqBid_shippingCharges", String.valueOf(rfqVendor.getShippingCharges()));
			incomingRequest.put("RfqBid_otherCharges", String.valueOf(rfqVendor.getOtherCharges()));
			incomingRequest.put("RfqBid_otherDescript", rfqVendor.getOtherDescription());
			incomingRequest.put("RfqBid_taxShipping", rfqVendor.getTaxShipping());
			incomingRequest.put("RfqBid_taxOther", rfqVendor.getTaxOther());
			incomingRequest.put("RfqBid_taxPercent", rfqVendor.getTaxAmount());
			incomingRequest.put("RfqBid_taxAmount", rfqVendor.getTaxAmount());
			incomingRequest.put("RfqBid_shippingTaxAmt", rfqVendor.getShippingTaxAmt());
			incomingRequest.put("RfqBid_otherTaxAmount", rfqVendor.getOtherTaxAmount());
*/			incomingRequest.put("RfqBid_bidCurrency", rfqVendor.getVendCurrency());
			incomingRequest.put("RfqBid_currencyFactor", String.valueOf(rfqVendor.getCurrencyFactor()));
			
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
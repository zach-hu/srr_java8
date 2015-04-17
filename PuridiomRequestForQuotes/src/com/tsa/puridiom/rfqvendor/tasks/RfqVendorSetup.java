package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class RfqVendorSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String	organizationId = (String) incomingRequest.get("organizationId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String today = Dates.today("", userTimeZone);

			incomingRequest.put("RfqVendor_icRfqHeader", "0");
			incomingRequest.put("RfqVendor_vendorId", "");
			incomingRequest.put("RfqVendor_dateResponseRecv", today);
			incomingRequest.put("RfqVendor_bidResponseCode", PropertiesManager.getInstance(organizationId).getProperty("RFQ DEFAULTS", "ResponseCode", ""));
			incomingRequest.put("RfqVendor_contactId", "");
			incomingRequest.put("RfqVendor_discountSource", "");
			incomingRequest.put("RfqVendor_discountPercent", "0");
			incomingRequest.put("RfqVendor_discountAmount", "0");
			incomingRequest.put("RfqVendor_shippingCharges", "0");
			incomingRequest.put("RfqVendor_otherCharges", "0");
			incomingRequest.put("RfqVendor_otherDescription", "");
			incomingRequest.put("RfqVendor_taxShipping", "");
			incomingRequest.put("RfqVendor_taxOther", "");
			incomingRequest.put("RfqVendor_taxCode", "");
			incomingRequest.put("RfqVendor_datePromised", today);
			incomingRequest.put("RfqVendor_taxPercent", "0");
			incomingRequest.put("RfqVendor_taxAmount", "0");
			incomingRequest.put("RfqVendor_shippingTaxAmt", "0");
			incomingRequest.put("RfqVendor_otherTaxAmount", "0");
			incomingRequest.put("RfqVendor_vendCurrency", "");
			incomingRequest.put("RfqVendor_fob", "");
			incomingRequest.put("RfqVendor_paymentTerms", "");
			incomingRequest.put("RfqVendor_bidValidTo", today);
			incomingRequest.put("RfqVendor_addressCode", "");
			incomingRequest.put("RfqVendor_ediRfq", "");
			incomingRequest.put("RfqVendor_dateEdiXmit", today);
			incomingRequest.put("RfqVendor_ediDateResponse", today);
			incomingRequest.put("RfqVendor_ediStatus", "01");
			incomingRequest.put("RfqVendor_discountTerms", "");
			incomingRequest.put("RfqVendor_notificationCode", "");
			incomingRequest.put("RfqVendor_contactName", "");
			incomingRequest.put("RfqVendor_bidsEntered", "N");
			incomingRequest.put("RfqVendor_vendorClass", "");
			incomingRequest.put("RfqVendor_currencyFactor", "1");
			incomingRequest.put("RfqVendor_evaluationStatus", "");
			incomingRequest.put("RfqVendor_totalScore", "0");

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

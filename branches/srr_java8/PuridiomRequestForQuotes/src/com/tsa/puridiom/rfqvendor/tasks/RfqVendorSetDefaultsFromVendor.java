package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class RfqVendorSetDefaultsFromVendor extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			if (incomingRequest.containsKey("vendor")) {
				Vendor vendor = (Vendor) incomingRequest.get("vendor");
				String organizationId = (String) incomingRequest.get("organizationId");
				RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");

				if (vendor != null) {
				    CurrencyManager currencyManager = CurrencyManager.getInstance(organizationId);
				    String currencyCode = vendor.getCurrencyCode();

				    if (Utility.isEmpty(currencyCode)) {
				        if (rfqHeader != null) {
				            currencyCode = rfqHeader.getCurrencyCode();
				        }
				    }
				    if (Utility.isEmpty(currencyCode)) {
				        currencyCode = currencyManager.getBaseCurrencyCode();
				    }
					incomingRequest.put("RfqVendor_vendorId", vendor.getVendorId());
					incomingRequest.put("RfqVendor_vendCurrency", currencyCode);
					incomingRequest.put("RfqVendor_fob", vendor.getFobId());
					incomingRequest.put("RfqVendor_vendorClass", vendor.getVendorClass());
					incomingRequest.put("RfqVendor_paymentTerms", vendor.getVendTerms());
					incomingRequest.put("RfqVendor_ediRfq", vendor.getPrintFaxCode());
					incomingRequest.put("RfqVendor_currencyFactor", String.valueOf(currencyManager.getCurrencyFactor(currencyCode)));

					if ( rfqHeader != null && rfqHeader.getDueDate() != null )
					{
						incomingRequest.put("RfqVendor_bidValidTo", rfqHeader.getDueDate().toString());
						incomingRequest.put("RfqVendor_datePromised", rfqHeader.getDueDate().toString());
					}
				}
			}
			if (incomingRequest.containsKey("address")) {
				Address address = (Address) incomingRequest.get("address");
				if (address != null) {
					incomingRequest.put("RfqVendor_addressCode", address.getComp_id().getAddressCode());
				}
			}
			if (incomingRequest.containsKey("contact")) {
				Contact contact = (Contact) incomingRequest.get("contact");
				if (contact != null) {
					incomingRequest.put("RfqVendor_contactId", contact.getComp_id().getContactCode());
				}
			}

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
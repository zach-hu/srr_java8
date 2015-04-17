package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqVendorAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqVendorAddHandler test = new RfqVendorAddHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqVendor_icRfqHeader", "827010200000") ;
			incomingRequest.put("RfqVendor_vendorId", "VENDOR-1");
			incomingRequest.put("RfqVendor_dateResponseRecv", "2003-11-18");
			incomingRequest.put("RfqVendor_bidResponseCode", "PROMPT");
			incomingRequest.put("RfqVendor_contactId", "000");
			incomingRequest.put("RfqVendor_discountPercent", "5");
			incomingRequest.put("RfqVendor_discountAmount", "32.00");
			incomingRequest.put("RfqVendor_shippingCharges", "11.5");
			incomingRequest.put("RfqVendor_otherCharges", "0");
			incomingRequest.put("RfqVendor_otherDescription", "");
			incomingRequest.put("RfqVendor_taxShipping", "");
			incomingRequest.put("RfqVendor_taxOther", "");
			incomingRequest.put("RfqVendor_taxCode", "");
			incomingRequest.put("RfqVendor_datePromised", "2004-01-31");
			incomingRequest.put("RfqVendor_taxPercent", "0");
			incomingRequest.put("RfqVendor_taxAmount", "0");
			incomingRequest.put("RfqVendor_shippingTaxAmt", "0");
			incomingRequest.put("RfqVendor_otherTaxAmount", "0");
			incomingRequest.put("RfqVendor_vendCurrency", "DOLLAR");
			incomingRequest.put("RfqVendor_fob", "");
			incomingRequest.put("RfqVendor_paymentTerms", "NET45");
			incomingRequest.put("RfqVendor_bidValidTo", "2003-12-14");
			incomingRequest.put("RfqVendor_addressCode", "001");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqVendorAdd - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqVendorUpdateByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqVendorRetrieveByHandler test = new RfqVendorRetrieveByHandler(); 
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqVendor_icRfqHeader", "827010200000");
			incomingRequest.put("RfqVendor_vendorId", "VENDOR-1");
			incomingRequest.put("RfqVendor_contactId", "002");
			incomingRequest.put("RfqVendor_discountPercent", "4");
			incomingRequest.put("RfqVendor_discountAmount", "28.00");
			incomingRequest.put("RfqVendor_shippingCharges", "12");
			incomingRequest.put("RfqVendor_otherCharges", "3.50");
			incomingRequest.put("RfqVendor_otherDescription", "This is an update test");
			incomingRequest.put("RfqVendor_taxShipping", "");
			incomingRequest.put("RfqVendor_taxOther", "");
			incomingRequest.put("RfqVendor_taxCode", "PA");
			incomingRequest.put("RfqVendor_datePromised", "2004-02-01");
			
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqVendorRetrieveBy - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
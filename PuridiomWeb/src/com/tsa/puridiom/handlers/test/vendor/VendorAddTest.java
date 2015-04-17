package com.tsa.puridiom.handlers.test.vendor;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class VendorAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			VendorAddHandler test = new VendorAddHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			incomingRequest.put("Vendor_vendorId", "PROCESS-TEST");
			incomingRequest.put("Vendor_vendorName", "Process test vendor");
			incomingRequest.put("Vendor_fobId", "fob");
			incomingRequest.put("Vendor_vendTerms", "NET30");			
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("VendorAdd - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
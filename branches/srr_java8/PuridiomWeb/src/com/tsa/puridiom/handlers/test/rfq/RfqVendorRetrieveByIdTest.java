package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqVendorRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqVendorRetrieveByIdHandler test = new RfqVendorRetrieveByIdHandler(); 
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqVendor_icRfqHeader", "602703700000");
			incomingRequest.put("RfqVendor_vendorId", "VENDOR-3");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqVendorRetrieveById - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqVendorAddFromListTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqVendorAddFromListHandler test = new RfqVendorAddFromListHandler();
			Map incomingRequest = new HashMap();
			String	vendorIds[] = {"GIANT", "TULA1"};
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqVendor_icRfqHeader", "602703700000");
			incomingRequest.put("RfqVendor_vendorId", vendorIds);
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			System.out.println("RfqVendorAddFromList - " + viewPage);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
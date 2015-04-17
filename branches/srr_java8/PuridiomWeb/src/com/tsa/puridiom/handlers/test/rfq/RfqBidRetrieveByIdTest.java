package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqBidRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqBidRetrieveByIdHandler test = new RfqBidRetrieveByIdHandler(); 
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqBid_icRfqHeader", "827010200000");
			incomingRequest.put("RfqBid_icRfqLine", "1146136500000");
			incomingRequest.put("RfqBid_vendorId", "VENDOR-3");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqBidRetrieveById - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
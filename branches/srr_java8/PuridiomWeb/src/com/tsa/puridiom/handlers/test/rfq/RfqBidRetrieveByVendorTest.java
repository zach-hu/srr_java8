package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqBidRetrieveByVendorTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqBidRetrieveByVendorHandler test = new RfqBidRetrieveByVendorHandler(); 
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqBid_icRfqHeader", "827010200000");
			incomingRequest.put("RfqBid_vendorId", "VENDOR-3");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqBidRetrieveByVendor - SUCCESS");
			}
			List list = (List) incomingRequest.get("rfqBidList");
			System.out.println("list size = " + list.size());
			for (int i=0; i < list.size(); i++)
			{
				RfqBid rfqBid = (RfqBid) list.get(i);
				System.out.println("Rfq Bid: " + rfqBid.toString());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
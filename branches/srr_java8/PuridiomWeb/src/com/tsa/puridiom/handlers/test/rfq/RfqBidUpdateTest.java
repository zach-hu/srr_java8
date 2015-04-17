package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqBidUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqBidUpdateHandler test = new RfqBidUpdateHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqBid_icRfqHeader","827010200000") ;
			incomingRequest.put("RfqBid_icRfqLine","1146143000000") ;
			incomingRequest.put("RfqBid_vendorId","VENDOR-3") ;
			incomingRequest.put("RfqBid_quantity","10") ;
			incomingRequest.put("RfqBid_umCode","EA") ;
			incomingRequest.put("RfqBid_umFactor", "1");
			incomingRequest.put("RfqBid_unitPrice","11.45") ;
			incomingRequest.put("RfqBid_bidCode","0") ;
			
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqBidUpdate single record - SUCCESS");
				RfqBid rfqBid = (RfqBid) incomingRequest.get("rfqBid");
				System.out.println(rfqBid.getComp_id().getVendorId() + " - " + rfqBid.getUnitPrice());
			}
			
			incomingRequest.remove("RfqBid_icRfqHeader");
			incomingRequest.remove("RfqBid_icRfqLine");
			incomingRequest.remove("RfqBid_vendorId");
			incomingRequest.remove("RfqBid_quantity");
			incomingRequest.remove("RfqBid_umCode");
			incomingRequest.remove("RfqBid_umFactor");
			incomingRequest.remove("RfqBid_unitPrice");
			incomingRequest.remove("RfqBid_bidCode");
			incomingRequest.remove("viewPage");			
			
			System.out.println("RfqBidUpdate multiple record test...");
			
			String	icRfqHeader[] = {"827010200000", "827010200000"};
			String	icRfqLine[] = {"1146143000000","1146136500000"};
			String	vendorId[] = {"VENDOR-3","VENDOR-3"};
			String	unitPrice[] = {"105.50", "33.55"};
			String	quantity[] = {"10", "25"};

			incomingRequest.put("RfqBid_icRfqHeader", icRfqHeader);
			incomingRequest.put("RfqBid_icRfqLine", icRfqLine);
			incomingRequest.put("RfqBid_vendorId", vendorId);
			incomingRequest.put("RfqBid_unitPrice", unitPrice);
			incomingRequest.put("RfqBid_quantity", quantity);
			
			test = new RfqBidUpdateHandler();
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqBidUpdate multiple record - SUCCESS");
			}
			
			System.out.println("RfqBidUpdateTest COMPLETE.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
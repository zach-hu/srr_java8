package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqLineBidsRetrieveByHeaderTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqLineBidsRetrieveByHeaderHandler test = new RfqLineBidsRetrieveByHeaderHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
//			incomingRequest.put("RfqLine_icRfqHeader", "602703700000");
			incomingRequest.put("RfqLine_icRfqHeader", "842856900000");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqLineBidsRetrieveByHeader - SUCCESS");
			}
			
			List list = (List) incomingRequest.get("rfqLineList");
			if (list != null)
			{
				for (int i = 0; i < list.size(); i++)
				{
					RfqLine rfqLine = (RfqLine) list.get(i);
					System.out.println(rfqLine.toString());

					List bids = rfqLine.getRfqBidList();
					if (bids != null)
					{
						for (int ib = 0; ib < bids.size(); ib++)
						{
							RfqBid bid = (RfqBid) bids.get(ib);
							System.out.println(bid.getComp_id().getVendorId() + " - " + String.valueOf(bid.getUnitPrice()));
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
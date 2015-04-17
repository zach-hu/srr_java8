package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqLineItemLookupTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqLineItemLookupHandler test = new RfqLineItemLookupHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqLine_icRfqHeader", "827010200000");
			incomingRequest.put("RfqLine_icRfqLine", "1146136500000");
			incomingRequest.put("RfqLine_rfqLine", "1");
			incomingRequest.put("RfqLine_itemNumber", "power");
			incomingRequest.put("RfqLine_catalogId", "sobe");
			incomingRequest.put("RfqLine_umCode", "EA");
			incomingRequest.put("RfqLine_quantity", "10");
			incomingRequest.put("RfqLine_taxable", "N");
			incomingRequest.put("RfqLine_status", "2000");
			incomingRequest.put("RfqLine_umFactor", "1");
			incomingRequest.put("RfqLine_description", "This is a test for updating rfq line items and bids!");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqLineItemLookup - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqLineItemLookupCreateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqLineItemLookupHandler test = new RfqLineItemLookupHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqHeader_icRfqHeader", "827010200000");
			incomingRequest.put("RfqLine_icRfqHeader", "827010200000");
//			incomingRequest.put("RfqLine_itemNumber", "power");
			incomingRequest.put("RfqLine_itemNumber", "test");
			incomingRequest.put("RfqLine_itemLocation", "100");
			incomingRequest.put("createAction", "SAVE");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqLineItemLookup Create - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
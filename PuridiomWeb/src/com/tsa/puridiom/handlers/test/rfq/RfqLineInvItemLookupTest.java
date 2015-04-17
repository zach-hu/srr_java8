package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqLineInvItemLookupTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvItemLookupHandler test = new InvItemLookupHandler();
			Map incomingRequest = new HashMap();
			String	itemLocations[] = {"43", "100", "60"};
			String itemNumbers[] = {"test", "test", "Mazda3"};
			String quantities[] = {"5", "10", "15"};
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("formtype", "RFQ");
			incomingRequest.put("icHeader", "827010200000");
/*			incomingRequest.put("InvLocation_itemLocation", itemLocations);
			incomingRequest.put("InvItem_itemNumber", itemNumbers);
			incomingRequest.put("quantity", quantities);
*/
			incomingRequest.put("InvLocation_itemLocation", "100");
			incomingRequest.put("InvItem_itemNumber", "test");
			incomingRequest.put("quantity", "3");
			incomingRequest.put("createAction", "SAVE");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			System.out.println("Rfq InvItemLookup - " + (String) incomingRequest.get("viewPage"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
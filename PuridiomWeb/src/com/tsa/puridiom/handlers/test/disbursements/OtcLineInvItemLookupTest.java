package com.tsa.puridiom.handlers.test.disbursements;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class OtcLineInvItemLookupTest
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
			incomingRequest.put("userId", "RENZO");
			incomingRequest.put("formtype", "DISB");
			incomingRequest.put("icHeader", "2586524700000");
/*			incomingRequest.put("InvLocation_itemLocation", itemLocations);
			incomingRequest.put("InvItem_itemNumber", itemNumbers);
			incomingRequest.put("quantity", quantities);
*/
			incomingRequest.put("InvLocation_itemLocation", "BT-4");
			incomingRequest.put("InvItem_itemNumber", "000001");
			incomingRequest.put("InvBinLocation_icRc", "1818773500035");
			incomingRequest.put("quantity", "3");
			incomingRequest.put("createAction", "SAVE");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			System.out.println("Otc InvItemLookup - " + (String) incomingRequest.get("viewPage"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
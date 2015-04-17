package com.tsa.puridiom.handlers.test.invitem;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class InvItemLookupTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			InvItemLookupHandler test = new InvItemLookupHandler();
			Map incomingRequest = new HashMap();
						
			String	itemNumbers[] = {"000011", "TSA Brochure", "TSA Brochure"};
			String	itemLocations[] = {"SOUTH", "SOUTH", "SOUTH"};
			String	quantities[] = {"5", "3", "3"};
			String	binIcs[] = {"2363587400076", "2344806800058", "2344808700059"};
			
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KAT");
			incomingRequest.put("formtype", "DSB");
			incomingRequest.put("icHeader", "3027456700004");
			incomingRequest.put("InvLocation_itemLocation", itemLocations);
			incomingRequest.put("InvItem_itemNumber", itemNumbers);
			incomingRequest.put("InvBinLocation_icRc", binIcs);
			incomingRequest.put("quantity", quantities);
			incomingRequest.put("createAction", "SAVE");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS"))
			{
				System.out.println("InvtemLookup - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
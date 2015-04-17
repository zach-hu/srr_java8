package com.tsa.puridiom.handlers.test.requisitions;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RequisitionLineCatalogItemLookupTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			CatalogItemLookupHandler test = new CatalogItemLookupHandler();
			Map incomingRequest = new HashMap();
			String	catalogIds[] = {"DELL", "DELL", "DELL"};
			String itemNumbers[] = {"310-2873", "320-0699", "320-4105"};
			String quantities[] = {"2", "20", "200"};
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("formtype", "REQ");
			incomingRequest.put("icHeader", "1679485300000");
			//incomingRequest.put("CatalogItem_catalogId", catalogIds);
			//incomingRequest.put("CatalogItem_itemNumber", itemNumbers);
			//incomingRequest.put("quantity", quantities);
			incomingRequest.put("CatalogItem_catalogId", null);
			incomingRequest.put("CatalogItem_itemNumber", "936");
			incomingRequest.put("quantity", "6");
			incomingRequest.put("createAction", "SAVE");
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
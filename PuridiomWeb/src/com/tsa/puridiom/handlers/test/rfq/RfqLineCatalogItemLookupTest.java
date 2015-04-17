package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqLineCatalogItemLookupTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			CatalogItemLookupHandler test = new CatalogItemLookupHandler();
			Map incomingRequest = new HashMap();
			String	catalogIds[] = {"936", "sobe1", "sobe"};
			String itemNumbers[] = {"10", "power", "power"};
			String quantities[] = {"2", "20", "200"};
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("formtype", "RFQ");
			incomingRequest.put("icHeader", "827010200000");
			incomingRequest.put("CatalogItem_catalogId", catalogIds);
			incomingRequest.put("CatalogItem_itemNumber", itemNumbers);
			incomingRequest.put("quantity", quantities);
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
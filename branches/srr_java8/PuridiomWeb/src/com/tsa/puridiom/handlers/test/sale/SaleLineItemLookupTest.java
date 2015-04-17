package com.tsa.puridiom.handlers.test.sale;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class SaleLineItemLookupTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			SaleLineItemLookupHandler test = new SaleLineItemLookupHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("icHeader", "6329061600000");
			incomingRequest.put("SaleHeader_icSaleHeader", "6329061600000");
			incomingRequest.put("SaleLine_icSaleLine", "6329061700002");
			incomingRequest.put("SaleLine_itemNumber", "SERVER");
			incomingRequest.put("SaleLine_itemLocation", "1087888");

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
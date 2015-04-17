package com.tsa.puridiom.handlers.test.sale;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class SaleGetFormNumberTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			SaleGetFormNumberHandler test = new SaleGetFormNumberHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("SaleHeader_icSaleHeader", "6329061600000");
			incomingRequest.put("SaleHeader_saleNumber", "");
			incomingRequest.put("SaleHeader_fiscalYear", "2005");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			System.out.println("Sale Number = " + (String) incomingRequest.get("SaleHeader_saleNumber"));
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("SaleGetFormNumber - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
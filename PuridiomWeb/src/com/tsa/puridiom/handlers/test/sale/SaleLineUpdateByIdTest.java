package com.tsa.puridiom.handlers.test.sale;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class SaleLineUpdateByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			SaleLineUpdateByIdHandler test = new SaleLineUpdateByIdHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("SaleLine_icSaleHeader", "6329061600000");
			incomingRequest.put("SaleLine_icSaleLine", "6329061700002");
			incomingRequest.put("SaleLine_itemNumber", "");
			incomingRequest.put("SaleLine_description", "This is a test to update the item");
			incomingRequest.put("SaleLine_quantity", "5");
			incomingRequest.put("SaleLine_umCode", "EACH");
			incomingRequest.put("SaleLine_umFactor", "1");
			incomingRequest.put("SaleLine_commodity", "OFFICE_SUPPLIES");
			incomingRequest.put("SaleLine_modelNumber", "123456");
			incomingRequest.put("SaleLine_mfgName", "STAPLES");
			
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("SaleLineUpdateById - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
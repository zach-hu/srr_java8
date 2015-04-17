package com.tsa.puridiom.handlers.test.sale;

import com.tsa.puridiom.entity.SaleHeader;
import com.tsa.puridiom.entity.SaleLine;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class SaleCreateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			SaleCreateHandler test = new SaleCreateHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			
			SaleHeader saleHeader = (SaleHeader) incomingRequest.get("saleHeader");
			SaleLine saleLine = (SaleLine) incomingRequest.get("saleLine");
			
			System.out.println("SALE HEADER - " + saleHeader.toString());
			System.out.println("SALE LINE - " + saleLine.toString());
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("SaleCreate - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
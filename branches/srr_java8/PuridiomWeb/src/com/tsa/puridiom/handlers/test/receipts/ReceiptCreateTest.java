package com.tsa.puridiom.handlers.test.receipts;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class ReceiptCreateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			ReceiptCreateRetrieveHandler test = new ReceiptCreateRetrieveHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("ReceiptHeader_icPoHeader", "1820627600008");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("ReceiptCreate - SUCCESS");
			}
			if (incomingRequest.containsKey("receiptHeader"))
			{
				ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
				if (receiptHeader != null)
				{
					System.out.println("ReceiptHeader: " + receiptHeader.toString());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
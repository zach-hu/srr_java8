package com.tsa.puridiom.handlers.test.receipts;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class ReceiptHeaderUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			ReceiptHeaderUpdateHandler test = new ReceiptHeaderUpdateHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("ReceiptHeader_icRecHeader", "2899169000000");
			incomingRequest.put("ReceiptHeader_icPoHeader", "1820627600008");
			incomingRequest.put("ReceiptHeader_pkgsReceived", "2");
			incomingRequest.put("ReceiptHeader_forwardTo", "KELLI");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("ReceiptHeaderUpdate - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
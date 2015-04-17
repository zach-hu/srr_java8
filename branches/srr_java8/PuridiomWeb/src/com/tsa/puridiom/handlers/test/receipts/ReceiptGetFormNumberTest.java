package com.tsa.puridiom.handlers.test.receipts;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class ReceiptGetFormNumberTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			ReceiptGetFormNumberHandler test = new ReceiptGetFormNumberHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("ReceiptHeader_icRecHeader", "111997");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("ReceiptGetFormNumber - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
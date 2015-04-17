package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqSaveasTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqSaveasHandler test = new RfqSaveasHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqHeader_icRfqHeader", "816401800000");
			incomingRequest.put("RfqHeader_rfqType", "RQ");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqSaveas - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
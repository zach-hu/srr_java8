package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqGetFormNumberTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqGetFormNumberHandler test = new RfqGetFormNumberHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqHeader_icRfqHeader", "1145085800000");
			incomingRequest.put("RfqHeader_rfqNumber", "");
			incomingRequest.put("RfqHeader_fiscalYear", "2003");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqGetFormNumber - SUCCESS");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqLineCreateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqLineCreateHandler test = new RfqLineCreateHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqHeader_icRfqHeader","1145085800000") ;
			incomingRequest.put("createAction", "SAVE");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");

			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);

			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqLineCreate - SUCCESS");
			}
			RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");
			System.out.println(rfqLine.getRfqLine() + " - " + rfqLine.getItemNumber());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
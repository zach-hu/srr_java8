package com.tsa.puridiom.handlers.test.rfq;

import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RfqCreateFromRequisitionTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqCreateFromRequisitionHandler test = new RfqCreateFromRequisitionHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RequisitionHeader_icReqHeader", "472282000000");
			incomingRequest.put("RfqHeader_rfqType", "RQ");
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);
			System.out.println(incomingRequest);
			
			String	viewPage = (String) incomingRequest.get("viewPage");
			if (viewPage != null && viewPage.equals("SUCCESS")) {
				System.out.println("RfqCreateFromRequisition - SUCCESS");
			}
			System.out.println("VIEW PAGE = " + (String) incomingRequest.get("viewPage"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
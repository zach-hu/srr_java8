package com.tsa.puridiom.requisition.tasks.tests;

import com.tsagate.foundation.processengine.*;

import java.util.*;

public class RequisitionDisplayRoutingTest
{
	public static void  main (String[] args) throws Exception
	{
	    System.out.println("start");
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisition-display-routing-list.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "JHUBBARD");
			incomingRequest.put("RequisitionHeader_icReqHeader", "3788820600029");
			incomingRequest.put("reqaction", "ROUTING");
			
			process.executeProcess(incomingRequest);
			
			System.out.println("routing list: " + incomingRequest.get("routingList"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("end");
	}
}
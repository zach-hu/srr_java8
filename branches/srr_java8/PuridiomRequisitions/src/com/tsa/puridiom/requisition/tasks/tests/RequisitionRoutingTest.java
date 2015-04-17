package com.tsa.puridiom.requisition.tasks.tests;

import com.tsagate.foundation.processengine.*;

import java.util.*;

public class RequisitionRoutingTest
{
	public static void  main (String[] args) throws Exception
	{
	    System.out.println("start");
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisition-routing-list.xml");
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "JHUBBARD");
			incomingRequest.put("RequisitionHeader_icReqHeader", "3996236800001");
			
			incomingRequest.put("reqaction", "FORWARD");
			process.executeProcess(incomingRequest);
			System.out.println("general Rules" + incomingRequest.get("rulList"));
			
			System.out.println("user Rules list" + incomingRequest.get("extList"));
			
			System.out.println("routing list: " + incomingRequest.get("routingList"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("end");
	}
}
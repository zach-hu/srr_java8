package com.tsa.puridiom.requisition.tasks.tests;

import com.tsagate.foundation.processengine.*;

import java.util.*;

public class RequisitionForwardToNextTest
{
	public static void  main (String[] args) throws Exception
	{
	    System.out.println("start");
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisition-forward-to-next.xml");
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "RRAMOS");
			incomingRequest.put("RequisitionHeader_icReqHeader", "3788820600029");
			incomingRequest.put("ApprovalLog_icHeader","3788820600029") ;
			
			incomingRequest.put("reqaction", "APPROVE");
			process.executeProcess(incomingRequest);
			System.out.println("Forwarded To - " + incomingRequest.get("forwardedTo"));
			System.out.println("New Requisition Status - " + incomingRequest.get("reqStatus"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("end");
	}
}
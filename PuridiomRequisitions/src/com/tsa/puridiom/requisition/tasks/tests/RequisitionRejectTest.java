package com.tsa.puridiom.requisition.tasks.tests;

import com.tsagate.foundation.processengine.*;

import java.util.*;

public class RequisitionRejectTest
{
	public static void  main (String[] args) throws Exception
	{
	    System.out.println("start");
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisition-reject.xml");
			Map incomingRequest = new HashMap();
			// TODO add your parameters to incomingRequest here
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KKNISELY");
			incomingRequest.put("RequisitionHeader_icReqHeader", "3788820600029");
			incomingRequest.put("ApprovalLog_icHeader","3788820600029") ;
			incomingRequest.put("ApprovalLog_approverNotes","I don't like you so I rejected this.") ;
			
			incomingRequest.put("reqaction", "APPROVE");
			process.executeProcess(incomingRequest);
			
			System.out.println("Rejected By - " + incomingRequest.get("rejectedBy"));
			System.out.println("New Requisition Status - " + incomingRequest.get("reqStatus"));
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("end");
	}
}
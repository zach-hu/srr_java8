package com.tsa.puridiom.requisition.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionSaveasTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisition-saveas.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "RRAMOS");
			incomingRequest.put("RequisitionHeader_icReqHeader", "2777636600001");
			incomingRequest.put("RequisitionHeader_requisitionType", "P");
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
			System.out.println("**************done***************************");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
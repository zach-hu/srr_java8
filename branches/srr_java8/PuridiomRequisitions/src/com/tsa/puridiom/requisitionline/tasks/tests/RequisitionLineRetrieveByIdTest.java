package com.tsa.puridiom.requisitionline.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionLineRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisitionline-retrieve-by-id.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("RequisitionLine_icReqLine", "489987700000");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
package com.tsa.puridiom.requisition.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisition-delete.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("RequisitionHeader_icReqHeader","1") ;
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
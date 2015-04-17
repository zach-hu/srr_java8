package com.tsa.puridiom.requisition.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisition-retrieve.xml");
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("RequisitionHeader_icReqHeader","832943700034") ;
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}

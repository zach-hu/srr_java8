package com.tsa.puridiom.receipt.tasks.tests;

import java.util.*;
import com.tsagate.foundation.processengine.*;

public class ReceiptCreateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("receipt-create.xml") ;
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("organizationId", "PURIDIOM");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
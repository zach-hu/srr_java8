package com.tsa.puridiom.receipt.tasks.tests;

import java.util.*;
import com.tsagate.foundation.processengine.*;

public class ReceiptRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("receipt-retrieve.xml") ;
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("ReceiptHeader_icRecHeader", "1119590600000");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
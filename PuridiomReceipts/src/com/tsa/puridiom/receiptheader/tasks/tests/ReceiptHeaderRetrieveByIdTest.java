package com.tsa.puridiom.receiptheader.tasks.tests;

import java.util.*;
import com.tsagate.foundation.processengine.*;

public class ReceiptHeaderRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("receiptheader-retrieve-by-id.xml") ;
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("ReceiptHeader_icRecHeader", "10");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
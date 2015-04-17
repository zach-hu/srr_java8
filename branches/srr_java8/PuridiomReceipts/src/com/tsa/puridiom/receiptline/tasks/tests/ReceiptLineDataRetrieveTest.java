package com.tsa.puridiom.receiptline.tasks.tests;

import java.util.*;
import com.tsagate.foundation.processengine.*;

public class ReceiptLineDataRetrieveTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("receiptlinedata-retrieve.xml") ;
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("ReceiptLine_icRecHeader", "1119590600000");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
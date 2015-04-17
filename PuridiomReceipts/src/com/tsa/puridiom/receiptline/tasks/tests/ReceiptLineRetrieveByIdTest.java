package com.tsa.puridiom.receiptline.tasks.tests;

import java.util.*;
import com.tsagate.foundation.processengine.*;

public class ReceiptLineRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("receiptline-retrieve-by-id.xml") ;
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("ReceiptLine_icRecHeader", "1119590600000");
			incomingRequest.put("ReceiptLine_icRecLine", "1");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
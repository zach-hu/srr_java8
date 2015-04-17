package com.tsa.puridiom.receiptline.tasks.tests;

import java.util.*;
import com.tsagate.foundation.processengine.*;

public class ReceiptLineUpdateByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("receiptline-update-by-id.xml") ;
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("ReceiptLine_icRecHeader", "1119590600000");
			incomingRequest.put("ReceiptLine_icRecLine", "1");
			incomingRequest.put("ReceiptLine_receiptNumber", "1");
			incomingRequest.put("ReceiptLine_receiptDate", "01-13-2004");
			incomingRequest.put("ReceiptLine_receivedBy", "KELLI");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
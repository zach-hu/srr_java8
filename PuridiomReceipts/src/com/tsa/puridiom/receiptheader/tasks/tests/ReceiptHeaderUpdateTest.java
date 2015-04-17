package com.tsa.puridiom.receiptheader.tasks.tests;

import java.util.*;
import com.tsagate.foundation.processengine.*;

public class ReceiptHeaderUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("receiptheader-update.xml") ;
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("ReceiptHeader_icRecHeader", "1119590600000");
			incomingRequest.put("ReceiptHeader_recType", "N");
			incomingRequest.put("ReceiptHeader_receiptNumber", "2004-0001");

			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
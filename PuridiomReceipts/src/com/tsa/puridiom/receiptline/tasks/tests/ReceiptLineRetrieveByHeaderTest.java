package com.tsa.puridiom.receiptline.tasks.tests;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptLineRetrieveByHeaderTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("receiptline-retrieve-by-header.xml") ;
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("ReceiptLine_icRecHeader", "1119590600000");

			process.executeProcess(incomingRequest);
			
			List list = (List) incomingRequest.get("receiptLineList");
			
			for (int i=0; i < list.size(); i++)
			{
				ReceiptLine receiptLine = (ReceiptLine) list.get(i);
				System.out.println("ReceiptLine " + i + ": " + receiptLine.toString());
			}
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
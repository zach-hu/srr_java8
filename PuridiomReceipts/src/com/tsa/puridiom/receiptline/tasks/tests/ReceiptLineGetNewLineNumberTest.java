package com.tsa.puridiom.receiptline.tasks.tests;

import com.tsa.puridiom.receiptline.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class ReceiptLineGetNewLineNumberTest
{
	public static void  main (String[] args)
	{
		try
		{
			ReceiptLineGetNewLineNumber test = new ReceiptLineGetNewLineNumber();
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("dbsession", new DBSession("PURIDIOM"));
			incomingRequest.put("ReceiptHeader_icRecHeader","1119590600000") ;
			
			String	lineNumber = (String) test.executeTask(incomingRequest);
			
			System.out.println("lineNumber =  " + lineNumber);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
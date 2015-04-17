package com.tsa.puridiom.receiptline.tasks.tests;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.receiptline.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptLineRetrieveAdjustmentsTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbsession = new DBSession("PURIDIOM");
			ReceiptLineRetrieveAdjustments test = new ReceiptLineRetrieveAdjustments();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession",dbsession);
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("ReceiptLine_receiptNumber", "000002");

			List list = (List) test.executeTask(incomingRequest);
			
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
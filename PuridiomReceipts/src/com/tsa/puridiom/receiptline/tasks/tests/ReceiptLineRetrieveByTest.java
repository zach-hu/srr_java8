package com.tsa.puridiom.receiptline.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.receiptline.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class ReceiptLineRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ReceiptLineRetrieveBy test = new ReceiptLineRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("ReceiptLine_icPoLine", "111222");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List receiptLineList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < receiptLineList.size(); i++)
			{
				ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(i);
				System.out.println("ReceiptLine: " + receiptLineList.toString());
			}
		
			System.out.println("ReceiptLineRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
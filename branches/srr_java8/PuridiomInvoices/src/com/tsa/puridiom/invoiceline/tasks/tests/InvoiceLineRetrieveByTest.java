package com.tsa.puridiom.invoiceline.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invoiceline.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvoiceLineRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InvoiceLineRetrieveBy test = new InvoiceLineRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List invoiceLineList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < invoiceLineList.size(); i++)
			{
				InvoiceLine invoiceLine = (InvoiceLine) invoiceLineList.get(i);
				System.out.println("InvoiceLine: " + invoiceLineList.toString());
			}
		
			System.out.println("InvoiceLineRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
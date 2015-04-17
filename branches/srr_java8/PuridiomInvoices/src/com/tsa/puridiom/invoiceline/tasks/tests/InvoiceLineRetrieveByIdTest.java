package com.tsa.puridiom.invoiceline.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invoiceline.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvoiceLineRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InvoiceLineRetrieveById test = new InvoiceLineRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			InvoiceLine invoiceLine = (InvoiceLine) test.executeTask(incomingRequest);
		
			System.out.println("InvoiceLine: " + invoiceLine.toString());
			System.out.println("InvoiceLineRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
package com.tsa.puridiom.invoiceline.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invoiceline.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvoiceLineAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InvoiceLineAdd test = new InvoiceLineAdd();
			Map incomingRequest = new HashMap();
		
			InvoiceLine invoiceLine = new InvoiceLine();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("invoiceLine", invoiceLine);
		
			invoiceLine = (InvoiceLine) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("InvoiceLineAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("InvoiceLineAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
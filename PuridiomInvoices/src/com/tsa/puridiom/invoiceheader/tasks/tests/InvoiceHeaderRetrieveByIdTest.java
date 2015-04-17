package com.tsa.puridiom.invoiceheader.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invoiceheader.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvoiceHeaderRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InvoiceHeaderRetrieveById test = new InvoiceHeaderRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			InvoiceHeader invoiceHeader = (InvoiceHeader) test.executeTask(incomingRequest);
		
			System.out.println("InvoiceHeader: " + invoiceHeader.toString());
			System.out.println("InvoiceHeaderRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
package com.tsa.puridiom.invoicevendor.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invoicevendor.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvoiceVendorRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InvoiceVendorRetrieveById test = new InvoiceVendorRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			InvoiceVendor invoiceVendor = (InvoiceVendor) test.executeTask(incomingRequest);
		
			System.out.println("InvoiceVendor: " + invoiceVendor.toString());
			System.out.println("InvoiceVendorRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
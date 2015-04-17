package com.tsa.puridiom.invoicevendor.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invoicevendor.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvoiceVendorRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InvoiceVendorRetrieveAll test = new InvoiceVendorRetrieveAll();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List invoiceVendorList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < invoiceVendorList.size(); i++)
			{
				InvoiceVendor invoiceVendor = (InvoiceVendor) invoiceVendorList.get(i);
				System.out.println("InvoiceVendor: " + invoiceVendorList.toString());
			}
		
			System.out.println("InvoiceVendorRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
package com.tsa.puridiom.invoicevendor.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invoicevendor.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvoiceVendorDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InvoiceVendorDeleteById test = new InvoiceVendorDeleteById();
			Map incomingRequest = new HashMap();
		
			InvoiceVendor invoiceVendor = new InvoiceVendor();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("invoiceVendor", invoiceVendor);
		
			invoiceVendor = (InvoiceVendor) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("InvoiceVendorDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("InvoiceVendorDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
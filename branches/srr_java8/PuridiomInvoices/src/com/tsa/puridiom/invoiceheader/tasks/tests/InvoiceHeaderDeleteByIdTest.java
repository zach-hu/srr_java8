package com.tsa.puridiom.invoiceheader.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invoiceheader.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvoiceHeaderDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InvoiceHeaderDeleteById test = new InvoiceHeaderDeleteById();
			Map incomingRequest = new HashMap();
		
			InvoiceHeader invoiceHeader = new InvoiceHeader();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("invoiceHeader", invoiceHeader);
		
			invoiceHeader = (InvoiceHeader) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("InvoiceHeaderDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("InvoiceHeaderDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
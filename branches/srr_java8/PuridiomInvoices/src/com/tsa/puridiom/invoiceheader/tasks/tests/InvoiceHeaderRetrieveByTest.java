package com.tsa.puridiom.invoiceheader.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invoiceheader.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvoiceHeaderRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InvoiceHeaderRetrieveBy test = new InvoiceHeaderRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List invoiceHeaderList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < invoiceHeaderList.size(); i++)
			{
				InvoiceHeader invoiceHeader = (InvoiceHeader) invoiceHeaderList.get(i);
				System.out.println("InvoiceHeader: " + invoiceHeaderList.toString());
			}
		
			System.out.println("InvoiceHeaderRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
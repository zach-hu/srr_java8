package com.tsa.puridiom.vendordocument.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendordocument.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class VendorDocumentRetrieveByHeaderTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorDocumentRetrieveByHeader test = new VendorDocumentRetrieveByHeader();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("VendorDocument_icRfqHeader", "123000");
			
			System.out.println("Database Status: " + dbs.getStatus());
		
			List vendorDocumentList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < vendorDocumentList.size(); i++)
			{
				VendorDocument vendorDocument = (VendorDocument) vendorDocumentList.get(i);
				System.out.println(i + " - VendorDocument: " + vendorDocumentList.toString());
			}
		
			System.out.println("VendorDocumentRetrieveByHeaderTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
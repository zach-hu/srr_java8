package com.tsa.puridiom.vendordocument.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendordocument.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;

import java.math.BigDecimal;
import java.util.*;

public class VendorDocumentRetrieveByVendorTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorDocumentRetrieveByVendor test = new VendorDocumentRetrieveByVendor();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("VendorDocument_icRfqHeader", "123000");
			incomingRequest.put("VendorDocument_vendorId", "TMP-VENDOR");
			
			System.out.println("Database Status: " + dbs.getStatus());
		
			List vendorDocumentList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < vendorDocumentList.size(); i++)
			{
				VendorDocument vendorDocument = (VendorDocument) vendorDocumentList.get(i);
				System.out.println(i + " - VendorDocument: " + vendorDocumentList.toString());
			}
		
			System.out.println("VendorDocumentRetrieveByVendorTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
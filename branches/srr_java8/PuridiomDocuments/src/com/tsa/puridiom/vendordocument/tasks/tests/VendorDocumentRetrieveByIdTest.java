package com.tsa.puridiom.vendordocument.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendordocument.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;

import java.math.BigDecimal;
import java.util.*;

public class VendorDocumentRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorDocumentRetrieveById test = new VendorDocumentRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("VendorDocument_docIc", "123456");
			incomingRequest.put("VendorDocument_icRfqHeader", "123000");
			incomingRequest.put("VendorDocument_vendorId", "TMP-VENDOR");
			
			System.out.println("Database Status: " + dbs.getStatus());
		
			VendorDocument vendorDocument = (VendorDocument) test.executeTask(incomingRequest);
		
			System.out.println("VendorDocument: " + vendorDocument.toString());
			System.out.println("VendorDocumentRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
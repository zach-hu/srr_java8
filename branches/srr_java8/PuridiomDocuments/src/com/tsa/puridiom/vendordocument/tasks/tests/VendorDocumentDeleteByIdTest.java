package com.tsa.puridiom.vendordocument.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendordocument.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class VendorDocumentDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorDocumentDeleteById test = new VendorDocumentDeleteById();
			Map incomingRequest = new HashMap();
		
			VendorDocument vendorDocument = new VendorDocument();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("vendorDocument", vendorDocument);
		
			vendorDocument = (VendorDocument) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("VendorDocumentDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("VendorDocumentDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
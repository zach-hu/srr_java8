package com.tsa.puridiom.vendordocument.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendordocument.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.math.BigDecimal;
import java.util.*;

public class VendorDocumentAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorDocumentAdd test = new VendorDocumentAdd();
			Map incomingRequest = new HashMap();
		
			VendorDocument vendorDocument = new VendorDocument();
			VendorDocumentPK pk = new VendorDocumentPK();
			pk.setDocIc(new BigDecimal(123458));
			pk.setIcRfqHeader(new BigDecimal(123000));
			pk.setVendorId("TMP-VENDOR2");
			vendorDocument.setComp_id(pk);
			vendorDocument.setDatePosted(new Date());
			vendorDocument.setDocFilename("123456.DOC");
			vendorDocument.setDocTitle("TEST-SUPPLIER DOCUMENT");
			
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("vendorDocument", vendorDocument);
		
			vendorDocument = (VendorDocument) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("VendorDocumentAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("VendorDocumentAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
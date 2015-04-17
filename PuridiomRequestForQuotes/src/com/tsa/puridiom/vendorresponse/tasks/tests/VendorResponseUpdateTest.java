package com.tsa.puridiom.vendorresponse.tasks.tests;

import com.tsa.puridiom.vendorresponse.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class VendorResponseUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorResponseUpdateEntityList test = new VendorResponseUpdateEntityList();
			Map incomingRequest = new HashMap();
			
			String	icRfqHeaderArray[] = {"756864200000", "756864200000"};
			String	icQuestionArray[] = {"781911900000", "781911900001"};
			String	vendorIdArray[] = {"OFFICEMAX", "OFFICEMAX"};
			String	responseArray[] = {"N", "Y"};
			String	textResponseArray[] = {"NA.", "We accept all major credit cards, checks, and cash."};
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("VendorResponse_icRfqHeader", icRfqHeaderArray);
			incomingRequest.put("VendorResponse_icQuestion", icQuestionArray);
			incomingRequest.put("VendorResponse_vendorId", vendorIdArray);
			incomingRequest.put("VendorResponse_response", responseArray);
			incomingRequest.put("VendorResponse_textResponse", textResponseArray);
			
			test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
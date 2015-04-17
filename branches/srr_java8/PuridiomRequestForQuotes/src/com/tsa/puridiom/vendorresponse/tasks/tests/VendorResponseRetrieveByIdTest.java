package com.tsa.puridiom.vendorresponse.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorresponse.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class VendorResponseRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorResponseRetrieveById test = new VendorResponseRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("VendorResponse_icRfqHeader", "756864200000");
			incomingRequest.put("VendorResponse_icQuestion", "781911900000");
			incomingRequest.put("VendorResponse_vendorId", "OFFICEMAX");			

			VendorResponse vendorResponse = (VendorResponse) test.executeTask(incomingRequest);
			System.out.println(incomingRequest);
			
			System.out.println("Response: " + vendorResponse.getResponse() + ", " + vendorResponse.getTextResponse());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
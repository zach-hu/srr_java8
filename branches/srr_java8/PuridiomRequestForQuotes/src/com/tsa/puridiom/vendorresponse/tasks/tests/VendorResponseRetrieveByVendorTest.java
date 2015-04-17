package com.tsa.puridiom.vendorresponse.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorresponse.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class VendorResponseRetrieveByVendorTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorResponseRetrieveByVendor test = new VendorResponseRetrieveByVendor();
			Map incomingRequest = new HashMap();
						
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("VendorResponse_icRfqHeader", "756864200000");
			incomingRequest.put("VendorResponse_vendorId", "OFFICEMAX");
			
			List list = (List) test.executeTask(incomingRequest);
			System.out.println(incomingRequest);
			
			for (int i=0; i < list.size(); i++)
			{
				VendorResponse vendorResponse = (VendorResponse) list.get(i);
				System.out.println(vendorResponse.getComp_id().getIcQuestion() + "/" + vendorResponse.getComp_id().getVendorId() + " - " + vendorResponse.getTextResponse());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
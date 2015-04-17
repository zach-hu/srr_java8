package com.tsa.puridiom.vendorcommrel.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorcommrel.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class VendorCommRelRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			VendorCommRelRetrieveById test = new VendorCommRelRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("VendorCommRel_vendorId", "TULA");
			incomingRequest.put("VendorCommRel_commodityCode", "OFFICE");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			VendorCommRel vendorCommRel = (VendorCommRel) test.executeTask(incomingRequest);
			System.out.println("VendorCommRel: " + vendorCommRel.toString());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
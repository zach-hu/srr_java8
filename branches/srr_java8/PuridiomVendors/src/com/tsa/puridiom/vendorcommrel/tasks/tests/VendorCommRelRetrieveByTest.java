package com.tsa.puridiom.vendorcommrel.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorcommrel.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class VendorCommRelRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			VendorCommRelRetrieveBy test = new VendorCommRelRetrieveBy();
			Map incomingRequest = new HashMap();

			incomingRequest.put("VendorCommRel_vendorId", "TULA");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			List list = (List) test.executeTask(incomingRequest);
			if (list != null)
			{
				for (int i = 0; i < list.size(); i++)
				{
					VendorCommRel vendorCommRel = (VendorCommRel) list.get(i);
					System.out.println(vendorCommRel.toString());
				}
			}

			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
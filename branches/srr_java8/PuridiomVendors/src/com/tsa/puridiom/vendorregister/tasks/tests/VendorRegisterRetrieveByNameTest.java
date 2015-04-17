package com.tsa.puridiom.vendorregister.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorregister.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class VendorRegisterRetrieveByNameTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			VendorRegisterRetrieveByName test = new VendorRegisterRetrieveByName();
			Map incomingRequest = new HashMap();

			incomingRequest.put("VendorRegister_vendorName", "Technical Services Associates");
			incomingRequest.put("dbsession", dbs);

			//system.out.println("Database Status: " + dbs.getStatus());

			List vendorRegisterList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < vendorRegisterList.size(); i++)
			{
				VendorRegister vendorRegister = (VendorRegister) vendorRegisterList.get(i);
				//system.out.println("VendorRegister: " + vendorRegister.toString());
			}

			//system.out.println("COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
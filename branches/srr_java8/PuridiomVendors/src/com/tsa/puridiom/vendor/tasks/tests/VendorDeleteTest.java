package com.tsa.puridiom.vendor.tasks.tests;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class VendorDeleteTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("vendor-delete.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("Vendor_vendorId", "TULA");
			process.executeProcess(incomingRequest);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
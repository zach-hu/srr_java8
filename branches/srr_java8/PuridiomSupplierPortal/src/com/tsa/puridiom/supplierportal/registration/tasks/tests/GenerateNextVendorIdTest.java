/*
 * Created on Mar 26, 2004
 */
package com.tsa.puridiom.supplierportal.registration.tasks.tests;

import com.tsa.puridiom.supplierportal.registration.tasks.*;

import java.util.*;

/**
 * @author kelli
 */
public class GenerateNextVendorIdTest {

	public static void  main (String[] args) throws Exception
	{
		try
		{
			GenerateNextVendorId test = new GenerateNextVendorId();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("vendorId", "TECSEA");
			
			String vendorId = (String) test.executeTask(incomingRequest);
			//system.out.println("GenerateNextVendorId Status: " + test.getStatus());
			//system.out.println("Vendor Id: " + vendorId);
			
			incomingRequest.put("vendorId", vendorId);
			
			vendorId = (String) test.executeTask(incomingRequest);
			//system.out.println("GenerateNextVendorId Status: " + test.getStatus());
			//system.out.println("Vendor Id: " + vendorId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

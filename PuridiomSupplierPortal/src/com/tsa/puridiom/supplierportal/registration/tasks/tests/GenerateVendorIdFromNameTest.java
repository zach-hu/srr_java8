/*
 * Created on Mar 26, 2004
 */
package com.tsa.puridiom.supplierportal.registration.tasks.tests;

import com.tsa.puridiom.supplierportal.*;
import com.tsa.puridiom.supplierportal.registration.tasks.*;
//import com.tsagate.foundation.database.*;
import java.util.*;

/**
 * @author kelli
 */
public class GenerateVendorIdFromNameTest {

	public static void  main (String[] args) throws Exception
	{
		try
		{
			GenerateVendorIdFromName test = new GenerateVendorIdFromName();
			Map incomingRequest = new HashMap();
			RegisterUser registerUser = new RegisterUser();
		
			registerUser.setVendorName("Technical Services, Inc.");
		
			incomingRequest.put("registerUser", registerUser);
			
			String vendorId = (String) test.executeTask(incomingRequest);
			//system.out.println("GenerateNewVendorId Status: " + test.getStatus());
			//system.out.println("Vendor Id: " + vendorId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

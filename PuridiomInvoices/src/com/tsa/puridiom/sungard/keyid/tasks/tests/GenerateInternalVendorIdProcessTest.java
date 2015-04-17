package com.tsa.puridiom.sungard.keyid.tasks.tests;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

import java.util.*;

public class GenerateInternalVendorIdProcessTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "SUNGARDEAS");
			incomingRequest.put("userId", "KELLI");

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("sungard-generate-internalvendorid.xml");
			process.executeProcess(incomingRequest);
			
			if (process.getStatus() == Status.SUCCEEDED) {
			    String internalVendorId = (String) incomingRequest.get("internalVendorId");
			    
			    System.out.println("internalVendorId - " + internalVendorId);
			    System.out.println("SUNGARD GENERATE INTERNAL VENDOR ID COMPLETE AND SUCCESSFUL");
			} else {
			    System.out.println("SUNGARD GENERATE INTERNAL VENDOR ID FAILED");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
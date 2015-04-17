package com.tsa.puridiom.sungard.extract.tasks.tests;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsa.puridiom.common.utility.HiltonUtility;

import java.util.*;

public class SungardInvoiceExtractTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "LEGION");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("InvoiceHeader_icIvcHeader","6710796400029") ;

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("sungard-invoice-extract.xml");
			process.executeProcess(incomingRequest);
			
			if (process.getStatus() == Status.SUCCEEDED) {
			    Object extractFile = incomingRequest.get("extractFile");
			    String	extractFileName = HiltonUtility.ckNull(extractFile).toString();
			    
			    System.out.println("EXTRACT CREATED FILE - " + extractFileName);
			    System.out.println("INVOICE SUNGARD EXTRACT COMPLETE AND SUCCESSFUL");
			} else {
			    System.out.println("INVOICE SUNGARD EXTRACT FAILED");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
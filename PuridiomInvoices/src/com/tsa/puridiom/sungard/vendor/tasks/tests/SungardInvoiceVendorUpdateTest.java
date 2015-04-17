package com.tsa.puridiom.sungard.vendor.tasks.tests;

import com.tsa.puridiom.sungard.tasks.SungardInvoiceVendorUpdate;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import java.util.HashMap;
import java.util.Map;

public class SungardInvoiceVendorUpdateTest
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
			PuridiomProcess process = processLoader.loadProcess("invoice-retrieve.xml");
			process.executeProcess(incomingRequest);
			
			if (process.getStatus() == Status.SUCCEEDED) {
			    SungardInvoiceVendorUpdate test = new SungardInvoiceVendorUpdate();
			    test.executeTask(incomingRequest);
			    
			    if (test.getStatus() == Status.SUCCEEDED) {
				    System.out.println("SungardInvoiceVendorUpdateTest COMPLETE AND SUCCESSFUL");
				} else {
				    System.out.println("SungardInvoiceVendorUpdate FAILED");
				}
			} else {
			    System.out.println("Invoice Retrieve FAILED");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
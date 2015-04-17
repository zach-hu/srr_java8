package com.tsa.puridiom.sungard.extract.tasks.tests;

import com.tsa.puridiom.sungard.extract.ExtractColumn;
import com.tsa.puridiom.sungard.extract.ExtractTemplate;
import com.tsa.puridiom.sungard.extract.tasks.GenerateInvoiceExtractTemplate;
import com.tsagate.foundation.processengine.Status;
import java.util.HashMap;
import java.util.Map;

public class GenerateInvoiceExtractTemplateTest
{
	public static void  main (String[] args) throws Exception
	{
		Object result = null;
		try {
			GenerateInvoiceExtractTemplate test = new GenerateInvoiceExtractTemplate();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "LEGION");
			
			ExtractTemplate extractObject = (ExtractTemplate) test.executeTask(incomingRequest);
			
			System.out.println("File Prefix = " + extractObject.getFilePrefix());
			System.out.println("File Extension = " + extractObject.getFileExtension());
			System.out.println("Extract Directory = " + extractObject.getExtractDirectory());
			System.out.println("Account Rollup = " + extractObject.isAccountRollup());
			
			ExtractColumn	extractColumns[] = extractObject.getExtractColumns();

			if (extractColumns != null) {
			    for (int i=0; i < extractColumns.length; i++) {
			        ExtractColumn extractColumn = extractColumns[i];
			        System.out.println("Extract Column " + extractColumn.toString());
			    }
			}
			
			if (test.getStatus() == Status.SUCCEEDED) {
			    System.out.println("GenerateInvoiceExtractTemplateTest SUCCEEDED");
			} else {
			    System.out.println("GenerateInvoiceExtractTemplateTest FAILED - Status = " + test.getStatus());
			}
			System.out.println("TEST COMPLETE");
		}
		catch(Exception exception) {
			System.out.println(exception.toString());
			throw exception;
		}
	}
}
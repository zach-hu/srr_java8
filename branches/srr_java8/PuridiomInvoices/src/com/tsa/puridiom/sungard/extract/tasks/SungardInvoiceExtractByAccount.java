package com.tsa.puridiom.sungard.extract.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.sungard.extract.ExtractColumn;
import com.tsa.puridiom.sungard.extract.ExtractTemplate;
import com.tsa.puridiom.sungard.extract.InvoiceExtractUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SungardInvoiceExtractByAccount extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map) object;

		String	organizationId = (String) incomingRequest.get("organizationId");
		String	userId = (String) incomingRequest.get("userId");
		InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
		List accountList = (List) incomingRequest.get("accountList");
		Vendor vendor = (Vendor) incomingRequest.get("vendor") ;
		ExtractTemplate extractTemplate = (ExtractTemplate) incomingRequest.get("extractTemplate");
		ExtractColumn extractHeader[] = extractTemplate.getExtractHeader() ;
		ExtractColumn extractDetail[] = extractTemplate.getExtractDetail() ;
		ExtractColumn extractColumns[] = extractTemplate.getExtractColumns();
		String nameLabels = null;
		nameLabels = HiltonUtility.ckNull(extractTemplate.getLabelName());

		File extractFile = (File) incomingRequest.get("extractFile");
		BufferedWriter bw = null;

		if (accountList != null && accountList.size() < 1) {
		    Log.debug(this, "No account allocations were found to extract.");
		    return null;
		}
		if (extractColumns == null || extractColumns.length <=0) {
		    Log.debug(this, "No columns were found in the template to extract.");
		    return null;
		}
		if (!extractFile.exists()) {
		    throw new Exception ("The batch file was not created.");
		}

	    try {
	    	boolean appending = false ;
	    	if (extractFile.length() == 0) {
	    		appending = true ;
	    	}
	        FileWriter fw = new FileWriter(extractFile, true);
	        bw = new BufferedWriter(fw);

	        if(!HiltonUtility.isEmpty(nameLabels))
	        {
	    		String subLine = "";
	        	for(int x = 0; x < nameLabels.length(); x++)
	        	{
	        		subLine = subLine + "-";
	        	}
	        	bw.write(nameLabels, 0, nameLabels.length());
	        	bw.newLine();
	        	bw.write(subLine, 0, subLine.length());
	        	bw.newLine();
	        }

	        if (accountList != null) {
	            for (int ia = 0; ia < accountList.size(); ia++) {

	                Account account = (Account) accountList.get(ia);

	                String exportText = "" ;
	                Map requestParameters = new HashMap();
	                requestParameters.put("organizationId", organizationId);
	                requestParameters.put("userId", userId);
	                requestParameters.put("invoiceHeader", invoiceHeader);
	    	        requestParameters.put("invoiceLine", null);
	    	        requestParameters.put("account", account);
	    	        requestParameters.put("vendor", vendor);
	    	        requestParameters.put("delimiter", extractTemplate.getDelimiter()) ;
		        	if (ia == 0 && accountList.size() > 0 && extractHeader.length > 0 && appending) {
	    	        	exportText =  InvoiceExtractUtility.generateExtractLine(extractHeader, requestParameters);
		                bw.newLine();
		                bw.write(exportText, 0, exportText.length());
	    	        }
		        	if (ia == 0 && extractDetail.length > 0) {
			               exportText = InvoiceExtractUtility.generateExtractLine(extractDetail, requestParameters);
		        	} else {
		        		  exportText = InvoiceExtractUtility.generateExtractLine(extractColumns, requestParameters);
		        	}

	                bw.newLine();
	                bw.write(exportText, 0, exportText.length());
	            }
		    }
			this.setStatus(Status.SUCCEEDED);
			incomingRequest.put("extractStatus", "SUCCESS") ;
       }
		catch (Exception e) {
		    throw e;
		}
		finally {
			if (bw != null) {
				try {
		            bw.close();
				}
				catch (IOException e) {
				    Log.error(this, e.getMessage());
				}
			}
		}
		return null;
	}
}
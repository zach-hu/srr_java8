package com.tsa.puridiom.sungard.extract.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.sungard.extract.ExtractColumn;
import com.tsa.puridiom.sungard.extract.ExtractTemplate;
import com.tsa.puridiom.sungard.extract.InvoiceExtractUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class SungardCheckRequestExtractByLine extends Task {

	private String organizationId = null ;
	private String userId = null ;
	private String nameLabels = null;
	private RequisitionHeader requisitionHeader = null ;
	private List requisitionLineList = null ;
	private Address address = null ;
	private List extractAccountList = null ;
	private File extractFile = null ;
	private BufferedWriter bw = null ;
	private ExtractColumn extractHeader[] = {} ;
	private ExtractColumn extractDetail[] = {} ;
	private ExtractColumn extractColumns[] = {} ;
	private boolean detailProcessed = false;

	private int lCount = 0;

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map) object;

		organizationId = (String) incomingRequest.get("organizationId");
		userId = (String) incomingRequest.get("userId");
		requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		requisitionLineList = (List) incomingRequest.get("requisitionLineList");
		List	extractAccountList = (List) incomingRequest.get("extractAccountList");
		address = (Address) incomingRequest.get("shipToAddress");
		Vendor vendor = (Vendor) incomingRequest.get("vendor") ;

		ExtractTemplate extractTemplate = (ExtractTemplate) incomingRequest.get("extractTemplate");
		String subLine = "";
		extractColumns = extractTemplate.getExtractColumns();
		extractHeader = extractTemplate.getExtractHeader() ;
		extractDetail = extractTemplate.getExtractDetail() ;

		nameLabels = HiltonUtility.ckNull(extractTemplate.getLabelName());
		extractFile = (File) incomingRequest.get("extractFile");


		if (requisitionLineList == null || requisitionLineList.size() <= 0) {
		    Log.debug(this, "No Check Request line items were found to extract.");
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
	        	for(int x = 0; x < nameLabels.length(); x++)
	        	{
	        		subLine = subLine + "-";
	        	}
	        	bw.write(nameLabels, 0, nameLabels.length());
	        	bw.newLine();
	        	bw.write(subLine, 0, subLine.length());
	        	bw.newLine();
	        }

	        for (int i = 0; i < requisitionLineList.size(); i++) {
			    RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(i);
			    List accountList = (List) extractAccountList.get(i) ;
	        	if (i == 0 && accountList.size() > 0 && extractHeader.length > 0 && appending) {
	        		Account account = (Account)accountList.get(0) ;
	                Map requestParameters = new HashMap();
	                requestParameters.put("organizationId", organizationId);
	                requestParameters.put("userId", userId);
	                requestParameters.put("requisitionHeader", requisitionHeader);
	    	        requestParameters.put("requisitionLine", requisitionLine);
	    	        requestParameters.put("account", account);
	    	        requestParameters.put("address", address);
	    	        requestParameters.put("vendor", vendor);
	    	        requestParameters.put("delimiter", extractTemplate.getDelimiter()) ;

	    	        String	exportText = InvoiceExtractUtility.generateExtractLine(extractHeader, requestParameters);
	                try {
	    	            bw.write(exportText, 0, exportText.length());
	    	            bw.newLine() ;
	                }

	           		catch (Exception e) {
	    			    Log.error(this, e.getMessage());
	           		}
	        	} else {
	        		if (i > 0 ||  extractHeader.length > 0) bw.newLine() ;
	        	}

                this.writeOut(accountList, requisitionLine) ;
			}

//			this.writeOut(invoiceHeader.getTaxAccountList()) ;
//			this.writeOut(invoiceHeader.getShippingAccountList()) ;
//			this.writeOut(invoiceHeader.getOtherAccountList()) ;

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

	private void writeOut(List accountList) {
		writeOut(accountList, null) ;
	}

	private void writeOut(List accountList, RequisitionLine requisitionLine) {

		if (accountList == null) {
			return ;
		}
		lCount = 0;
        for (int ia = 0; ia < accountList.size(); ia++) {
            Account account = (Account) accountList.get(ia);

            Map requestParameters = new HashMap();
            requestParameters.put("organizationId", organizationId);
            requestParameters.put("userId", userId);
            requestParameters.put("requisitionHeader", requisitionHeader);
	        requestParameters.put("requisitionLine", requisitionLine);
	        requestParameters.put("account", account);
	        requestParameters.put("address", address);

	        String exportText = "" ;
	        if (ia == 0 && extractDetail.length > 0 && ! this.detailProcessed) {
	        	this.detailProcessed = true ;
	        	exportText = InvoiceExtractUtility.generateExtractLine(extractDetail, requestParameters);
	        } else {
	        	exportText = InvoiceExtractUtility.generateExtractLine(extractColumns, requestParameters);
	        }

            try {
	            if (lCount > 0) bw.newLine();
	            bw.write(exportText, 0, exportText.length());
            }
       		catch (Exception e) {
			    Log.error(this, e.getMessage());
       		}
            lCount++ ;
        }
	}
}
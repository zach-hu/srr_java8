package com.tsa.puridiom.sungard.extract.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.sungard.extract.ExtractColumn;
import com.tsa.puridiom.sungard.extract.ExtractTemplate;
import com.tsa.puridiom.sungard.extract.InvoiceExtractUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class SungardInvoiceExtractUpdateHeader extends Task {

	private String organizationId = null ;
	private String userId = null ;
	private InvoiceHeader invoiceHeader = null ;
	private RequisitionHeader requisitionHeader = null ;
	private List invoiceLineList = null ;
	private List requisitionLineList = null ;
	private Address address = null ;
	private List extractAccountList = null ;
	private File extractFile = null ;
	private BufferedWriter bw = null ;
	private ExtractColumn extractHeader[] = {} ;
	private int lCount = 0;

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map) object;

		organizationId = (String) incomingRequest.get("organizationId");
		userId = (String) incomingRequest.get("userId");
		invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
		invoiceLineList = (List) incomingRequest.get("invoiceLineList");
		requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		requisitionLineList = (List) incomingRequest.get("requisitionLineList");
		List	extractAccountList = (List) incomingRequest.get("extractAccountList");
		address = (Address) incomingRequest.get("shipToAddress");

		ExtractTemplate extractTemplate = (ExtractTemplate) incomingRequest.get("extractTemplate");
		extractHeader = extractTemplate.getExtractHeader() ;
		String extractStatus = (String) incomingRequest.get("extractStatus")  ;
		if (extractStatus == null) extractStatus = "" ;

		if (extractHeader.length > 0 && extractStatus.equalsIgnoreCase("SUCCESS")) {
			// Only update the counter if (invoiceCount) is found
			for (int ix = 0; ix < extractHeader.length; ix++) {
				ExtractColumn ex = extractHeader [ix] ;
				if (ex.getColumnName().equalsIgnoreCase("Compute_tranCounter")) {
					int st = ex.getStartPosition() ;
					int sz = ex.getSize() ;
					byte[] buffer = new byte[sz]  ;
					extractFile = (File) incomingRequest.get("extractFile");

		            RandomAccessFile rf = new RandomAccessFile(extractFile, "rw");
		            try {
			            long pos =(long) st - 1 ;
			            rf.seek(pos) ;
			            rf.read(buffer, 0, sz ) ;

			            String sCount = new String(buffer) ;
			            if (sCount != null) {
			            	sCount = sCount.trim() ;
			            	if (sCount.length() == 0) sCount = "0";
			            	long count = Long.parseLong(sCount) ;
			            	count++ ;
			            	sCount = Long.toString(count) ;
			            	if (ex.getJustify().equalsIgnoreCase("R")) {
			            		while (sCount.length() < sz) {
			            			sCount = " " + sCount  ;
			            		}
			            	}
			            	rf.seek(pos) ;
				            rf.write(sCount.getBytes()) ;
			            }
					}	catch (Exception e) {
						    throw e;
						} 	finally {
							if (rf != null) {
								try {
						            rf.close();
								}
								catch (IOException e) {
								    Log.error(this, e.getMessage());
								}
							}
					}
				}
			}
		}
		this.setStatus(Status.SUCCEEDED);
		return null;
	}
}
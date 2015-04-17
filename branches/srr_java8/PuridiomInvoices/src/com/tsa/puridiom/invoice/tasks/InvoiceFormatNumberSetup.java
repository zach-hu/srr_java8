package com.tsa.puridiom.invoice.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli
 */
public class InvoiceFormatNumberSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try {
			String	invoiceNumber = (String) incomingRequest.get("InvoiceHeader_invoiceNumber");
			String	fiscalYear = (String) incomingRequest.get("InvoiceHeader_fiscalYear");
			if (fiscalYear == null || fiscalYear.trim().length() == 0) {
				fiscalYear = "1994";
			}
			incomingRequest.put("AutoGen_documentType", "INV") ;
			incomingRequest.put("AutoGen_genYear", fiscalYear) ;
			incomingRequest.put("documentNumber", invoiceNumber);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}

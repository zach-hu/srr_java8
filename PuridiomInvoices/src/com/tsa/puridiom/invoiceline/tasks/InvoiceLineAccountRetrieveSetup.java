/*
 * Created on October 31, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

/**
 * @author kathleen
 */
public class InvoiceLineAccountRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		try {
			String icHeader = "";
			String icLine = "";

			if (incomingRequest.containsKey("invoiceLine")) {
				InvoiceLine ivl = (InvoiceLine) incomingRequest.get("invoiceLine");
			    if (ivl != null) {
			        icHeader = ivl.getIcIvcHeader().toString();
			        icLine = ivl.getIcIvcLine().toString();
			    }
			}
			if (Utility.isEmpty(icHeader) && Utility.isEmpty(icLine)) {
			    icHeader = (String) incomingRequest.get("InvoiceLine_icIvcHeader");
			    icLine = (String) incomingRequest.get("InvoiceLine_icIvcLine");
			}

			incomingRequest.put("Account_icHeader",icHeader);
	        incomingRequest.put("Account_icLine",icLine);

	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}

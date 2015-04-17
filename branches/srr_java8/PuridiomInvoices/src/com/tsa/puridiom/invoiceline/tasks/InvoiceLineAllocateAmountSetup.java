/*
 * Created on September 20, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class InvoiceLineAllocateAmountSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		try {
			String icHeader = (String)incomingRequest.get("Account_icHeader");
			String icLine = (String)incomingRequest.get("Account_icLine");
			incomingRequest.put("InvoiceHeader_icIvcHeader",icHeader);
	        incomingRequest.put("InvoiceHeader_icIvcLine",icLine);
	        incomingRequest.put("InvoiceLine_icIvcLine",icLine);
	        this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null;
	}

}

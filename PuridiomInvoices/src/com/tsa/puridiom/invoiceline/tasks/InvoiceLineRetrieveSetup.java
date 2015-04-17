/*
 * Created on October 05, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.util.*;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author kathleen
 */
public class InvoiceLineRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED);

		InvoiceLine ivl = (InvoiceLine) incomingRequest.get("invoiceLine");
		if (ivl == null) {
			this.setStatus(Status.FAILED);
		} else {
			String icHeader = ivl.getIcIvcHeader().toString() ;
			String icLine = ivl.getIcIvcLine().toString() ;
			String icPoLine = ivl.getIcPoLine().toString();

			if (Utility.isEmpty(icHeader)) {
				this.setStatus(Status.FAILED);
			} else {
				incomingRequest.put("InvoiceLine_icIvcLine",icLine);
				incomingRequest.put("InvoiceLine_icPoLine",icPoLine);
				incomingRequest.put("PoLine_icPoLine",icPoLine);
				incomingRequest.put("Account_icHeader",icHeader);
				incomingRequest.put("Account_icLine",icLine);
			}
		}

		return null ;
	}
}

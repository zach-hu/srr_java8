package com.tsa.puridiom.invoice.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

@SuppressWarnings(value = { "unchecked" })
public class InvoiceRecallSetup extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		String icIvcHeader = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");

		if (Utility.isEmpty(icIvcHeader)) {
			this.setStatus(Status.FAILED);
		} else {
			incomingRequest.put("InvoiceLine_icIvcHeader",icIvcHeader) ;
			this.setStatus(Status.SUCCEEDED);
		}

		incomingRequest.put("newStatus", DocumentStatus.IVC_RECALLED);

		return null ;
	}
}

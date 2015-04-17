package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvoiceRoutingDisplaySetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String icIvcHeader = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");

		incomingRequest.put("ApprovalLog_icHeader", icIvcHeader);


		if (!incomingRequest.containsKey("InvoiceHeader_status")) {
		    InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
		    if (invoiceHeader != null) {
		        incomingRequest.put("InvoiceHeader_status", invoiceHeader.getStatus());
		    }
		}

		return null;
	}
}

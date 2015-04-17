package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.entity.InvoiceLine;
//import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.processengine.Status;
import java.util.Map;

public class InvoiceLineUpdateSetup extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		InvoiceLine ivc =(InvoiceLine) incomingRequest.get("invoiceLine");

		incomingRequest.put("InvoiceHeader_icIvcHeader",ivc.getIcIvcHeader().toString());

		incomingRequest.put("Account_icHeader",ivc.getIcIvcHeader().toString());
		incomingRequest.put("Account_icLine", ivc.getIcIvcLine().toString());

		this.status = Status.SUCCEEDED;

		return null;
	}
}
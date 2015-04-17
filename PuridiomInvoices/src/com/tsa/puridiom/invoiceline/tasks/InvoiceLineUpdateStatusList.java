package com.tsa.puridiom.invoiceline.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class InvoiceLineUpdateStatusList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	invoiceLineList = (List) incomingRequest.get("invoiceLineList");


			for (int i=0; i < invoiceLineList.size(); i++) {
				InvoiceLine invoiceLine = (InvoiceLine) invoiceLineList.get(i);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("invoiceline-update-status.xml");

				Map newIncomingRequest = new HashMap();

				newIncomingRequest.put("InvoiceLine_icIvcLine", invoiceLine.getIcIvcLine().toString());
				newIncomingRequest.put("invoiceLine", invoiceLine);
				newIncomingRequest.put("ivcHeader", incomingRequest.get("invoiceHeader"));
				newIncomingRequest.put("organizationId",incomingRequest.get("organizationId"));
				newIncomingRequest.put("userId",incomingRequest.get("userId"));
				newIncomingRequest.put("dbsession",incomingRequest.get("dbsession"));
				newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
				newIncomingRequest.put("InvoiceLine_status", incomingRequest.get("newStatus"));
				newIncomingRequest.put("newHistoryInvoiceLine", invoiceLine);

				process.executeProcess(newIncomingRequest);

				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("Invoice Line save as process failed.");
				}
			}

			result = invoiceLineList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
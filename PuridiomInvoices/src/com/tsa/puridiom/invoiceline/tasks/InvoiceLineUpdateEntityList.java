package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvoiceLineUpdateEntityList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List invoiceLineList = (List) incomingRequest.get("invoiceLineList");
			InvoiceLineUpdateById invoiceLineUpdateTask = new InvoiceLineUpdateById();

			for (int i = 0; i < invoiceLineList.size(); i++) {
				InvoiceLine invoiceLine = (InvoiceLine) invoiceLineList.get(i);

				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("invoiceLine", invoiceLine);

				invoiceLineUpdateTask.executeTask(updateParameters);

				if (invoiceLineUpdateTask.getStatus() < Status.SUCCEEDED) {
					throw new Exception("InvoiceLineUpdateById failed.");
				}
			}

			result = invoiceLineList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
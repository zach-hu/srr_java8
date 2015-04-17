package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvoiceLineUpdateList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	ivcLineList = (List) incomingRequest.get("invoiceLineList");

			for (int i=0; i < ivcLineList.size(); i++) {
				InvoiceLine ivcLine = (InvoiceLine) ivcLineList.get(i);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("invoiceline-update-norecalc.xml");

				incomingRequest.put("invoiceLine", ivcLine);

				process.executeProcess(incomingRequest);

				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("Invoice Line save as process failed.");
				}
			}

			result = ivcLineList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
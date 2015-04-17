package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvoiceLineSetInvoiceNumberFromHeader extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List invoiceLineList = (List) incomingRequest.get("invoiceLineList");
			String invoiceNumber = (String) incomingRequest.get("InvoiceHeader_invoiceNumber");

			for (int i = 0; i < invoiceLineList.size(); i++)
			{
				InvoiceLine invoiceLine = (InvoiceLine) invoiceLineList.get(i);
				invoiceLine.setInvoiceNumber(invoiceNumber);
				invoiceLineList.set(i, invoiceLine);
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
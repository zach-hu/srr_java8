package com.tsa.puridiom.sungard.extract.tasks;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SungardInvoiceExtractByAccountSetup extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;

		String	organizationId = (String) incomingRequest.get("organizationId");
		String	userId = (String) incomingRequest.get("userId");
		InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
		List invoiceLineList = (List) incomingRequest.get("invoiceLineList");
		List accountList = new ArrayList();

	    try {
	        List	defaultAccountList = invoiceHeader.getAccountList();
	        if (defaultAccountList != null) {
	            accountList.addAll(defaultAccountList);
	        }
            for (int i = 0; i < invoiceLineList.size(); i++) {
                InvoiceLine invoiceLine = (InvoiceLine) invoiceLineList.get(i);

                List	lineAccountList = invoiceLine.getAccountList();
                if (lineAccountList != null) {
                    accountList.addAll(lineAccountList);
                }
            }

            result = accountList;

			this.setStatus(Status.SUCCEEDED);
        }
		catch (Exception e) {
		    throw e;
		}
		return result;
	}
}
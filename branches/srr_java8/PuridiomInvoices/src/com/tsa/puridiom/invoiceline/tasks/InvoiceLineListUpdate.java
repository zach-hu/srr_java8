package com.tsa.puridiom.invoiceline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class InvoiceLineListUpdate extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List invoiceLineList = (List)incomingRequest.get("invoiceLineList");
			
			for (int i=0; i < invoiceLineList.size(); i++) 
			{
				InvoiceLine invoiceLine = (InvoiceLine) invoiceLineList.get(i);

				InvoiceLineUpdate update = new InvoiceLineUpdate();
				
				incomingRequest.put("invoiceLine", invoiceLine);
				
				update.executeTask(incomingRequest);
			}
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
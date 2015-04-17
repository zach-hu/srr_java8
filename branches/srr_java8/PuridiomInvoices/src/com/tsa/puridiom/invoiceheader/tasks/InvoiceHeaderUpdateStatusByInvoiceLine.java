package com.tsa.puridiom.invoiceheader.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class InvoiceHeaderUpdateStatusByInvoiceLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		try{
			Map incomingRequest = (Map)object;
			String oid = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);

			List invoiceLineList = (List)incomingRequest.get("invoiceLineList");
			InvoiceHeader invoiceHeader = (InvoiceHeader)incomingRequest.get("invoiceHeader");
			StringBuffer status = new StringBuffer();
			int lowestStatus = 99999;
			if(invoiceLineList != null && invoiceLineList.size() > 0)
			{
				for (int i = 0; i < invoiceLineList.size(); i++) {
					InvoiceLine invoicetLine = (InvoiceLine)invoiceLineList.get(i);
					String invoiceLineStatus = invoicetLine.getStatus();
					if(lowestStatus > Integer.parseInt(invoiceLineStatus)){
						lowestStatus = Integer.parseInt(invoiceLineStatus);
					}
				}

				status.append(lowestStatus);

				if(!invoiceHeader.getStatus().equalsIgnoreCase(status.toString())){
					PuridiomProcess process = processLoader.loadProcess("invoice-history.xml");
					
					invoiceHeader.setStatus(status.toString());
	
					InvoiceHeaderUpdate update = new InvoiceHeaderUpdate();
					Map updateIncomingRequest = new HashMap();
					updateIncomingRequest.put("invoiceHeader", invoiceHeader);
					updateIncomingRequest.put("organizationId", oid);
					updateIncomingRequest.put("userId", userId);
					updateIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
					update.executeTask(updateIncomingRequest);
					
					updateIncomingRequest.put("newHistoryInvoiceHeader", invoiceHeader);
					process.executeProcess(updateIncomingRequest);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			e.printStackTrace();
		}
		return result;
	}

}

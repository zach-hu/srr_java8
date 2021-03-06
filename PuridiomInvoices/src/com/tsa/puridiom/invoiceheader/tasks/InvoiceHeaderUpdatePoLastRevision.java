package com.tsa.puridiom.invoiceheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class InvoiceHeaderUpdatePoLastRevision extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			
			List invoiceHeaderList = (List)incomingRequest.get("invoiceHeaderList");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			
			if(poHeader != null && invoiceHeaderList != null && invoiceHeaderList.size() > 0)
			{
				PuridiomProcess process = processLoader.loadProcess("invoiceline-update-po-lastrevision.xml");
				
				for (int i = 0; i < invoiceHeaderList.size(); i++) {
					InvoiceHeader invoiceHeader = (InvoiceHeader)invoiceHeaderList.get(i);
					
					if(invoiceHeader.getStatus().compareToIgnoreCase(DocumentStatus.IVC_INPROGRESS) == 0){
						invoiceHeader.setIcPoHeader(poHeader.getIcPoHeader());
						
						InvoiceHeaderUpdate update = new InvoiceHeaderUpdate();
						incomingRequest.put("invoiceHeader", invoiceHeader);
						update.executeTask(incomingRequest);
						
						incomingRequest.put("PoLine_icPoHeader", poHeader.getIcPoHeader().toString());
						incomingRequest.put("InvoiceLine_icIvcHeader", invoiceHeader.getIcIvcHeader().toString());
						
						process.executeProcess(incomingRequest);
					}
				}
			} else {
				Log.error(this, "invoiceHeaderList or poHeader is empty! in InvoiceHeaderUpdatePoLastRevision");
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
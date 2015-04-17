package com.tsa.puridiom.invoiceline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class InvoiceLineUpdatePoLastRevision extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			List invoiceLineList = (List)incomingRequest.get("invoiceLineList");
			List poLineList = (List)incomingRequest.get("poLineList");
			
			if(invoiceLineList != null && invoiceLineList.size() > 0)
			{
				for (int i = 0; i < invoiceLineList.size(); i++) {
					InvoiceLine invoiceLine = (InvoiceLine)invoiceLineList.get(i);
					PoLine poLine = (PoLine)poLineList.get(i);
					
					if(invoiceLine.getIcPoLine().compareTo(poLine.getIcLineKey()) == 0){
						invoiceLine.setIcPoLine(poLine.getIcPoLine());
						invoiceLine.setIcPoHeader(poLine.getIcPoHeader());
					} else {
						for (int j = 0; j < poLineList.size(); j++) {
							poLine = (PoLine)poLineList.get(j);
							
							if(invoiceLine.getIcPoLine().compareTo(poLine.getIcLineKey()) == 0){
								invoiceLine.setIcPoLine(poLine.getIcPoLine());
								invoiceLine.setIcPoHeader(poLine.getIcPoHeader());
							} else {
								Log.info(this, "InvoiceLine has not been updated, there is not PoLine relationated");
							}
						}
					}
					
				}
			} else {
				Log.error(this, "invoiceLineList or poHeader is empty! in InvoiceLineUpdatePoLastRevision");
			}
			
			result = invoiceLineList;
			
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			e.printStackTrace();
		}

		return result ;
	}
}
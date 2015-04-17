package com.tsa.puridiom.invoiceheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings(value = { "unchecked" })
public class InvoiceHeaderDocumentsRetrieveSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;

		InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader") ;
		if (invoiceHeader == null)
		{
			this.setStatus(Status.FAILED) ;
			throw new TsaException("InvoiceHeader Entity was not found for InvoiceHeaderDocumentsRetrieveSetup!");
		}
		else
		{
			incomingRequest.put("InvoiceLine_icIvcHeader", invoiceHeader.getIcIvcHeader().toString());
			incomingRequest.put("PoHeader_icPoHeader", invoiceHeader.getIcPoHeader().toString());
		}
		
		this.setStatus(Status.SUCCEEDED) ;
		return null;
	}
}
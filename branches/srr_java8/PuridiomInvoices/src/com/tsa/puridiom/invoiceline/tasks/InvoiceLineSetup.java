package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.Map;

public class InvoiceLineSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("InvoiceLine_icIvcLine",	ukg.getUniqueKey().toString());
			incomingRequest.put("InvoiceLine_icLineHistory", ukg.getUniqueKey().toString());
			/*
			incomingRequest.put("InvoiceLine_icPoHeader", "0");
			incomingRequest.put("InvoiceLine_icPoLine", "0");
			incomingRequest.put("InvoiceLine_invoiceNumber", "");
			incomingRequest.put("InvoiceLine_lineNumber", "0");
			*/
			incomingRequest.put("InvoiceLine_itemNumber", (String) incomingRequest.get("InvoiceLine_itemNumber"));
			incomingRequest.put("InvoiceLine_asset", "N");
			/*
			incomingRequest.put("InvoiceLine_description", "");
			incomingRequest.put("InvoiceLine_quantity", "0");
			incomingRequest.put("InvoiceLine_umCode", "");
			incomingRequest.put("InvoiceLine_umFactor", "0");
			incomingRequest.put("InvoiceLine_unitPrice", "0");
			incomingRequest.put("InvoiceLine_taxable", "");
			incomingRequest.put("InvoiceLine_taxPercent", "0");
			incomingRequest.put("InvoiceLine_taxAmount", "0");
			incomingRequest.put("InvoiceLine_discountPercent", "0");
			incomingRequest.put("InvoiceLine_discountAmount", "0");
			incomingRequest.put("InvoiceLine_lineTotal", "0");
			*/

			incomingRequest.put("InvoiceLine_status", DocumentStatus.IVC_INPROGRESS);
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
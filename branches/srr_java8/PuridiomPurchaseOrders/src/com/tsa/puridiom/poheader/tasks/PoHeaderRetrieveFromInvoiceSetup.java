/**
 *
 * Created on April 20, 2007
 * @author Kathleen
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderRetrieveFromInvoiceSetup.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.*;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;

public class PoHeaderRetrieveFromInvoiceSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED);

		InvoiceHeader ivh = (InvoiceHeader) incomingRequest.get("invoiceHeader");
		if (ivh == null)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Invoice Header Entity was not found for PoHeaderRetrieveFromInvoiceSetup!");
		}
		else
		{
			String icHeader = ivh.getIcPoHeader().toString();

			if (Utility.isEmpty(icHeader))
			{
				this.setStatus(Status.FAILED);
			}
			else
			{
				incomingRequest.put("PoHeader_icPoHeader", icHeader);
			}
		}
		return null;
	}
}
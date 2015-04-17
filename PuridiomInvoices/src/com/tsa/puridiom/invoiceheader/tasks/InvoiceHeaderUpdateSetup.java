/*
 * Created on May 26, 2006
 */
package com.tsa.puridiom.invoiceheader.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvoiceHeaderUpdateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;
		try
		{
			incomingRequest.put("InvoiceLine_icIvcHeader", incomingRequest.get("InvoiceHeader_icIvcHeader")) ;
			incomingRequest.put("Account_icHeader", incomingRequest.get("InvoiceHeader_icIvcHeader"));
			incomingRequest.put("Account_icLine", "0");
			incomingRequest.put("Default_referenceType", "IVH");

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}

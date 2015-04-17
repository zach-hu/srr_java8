/**
 * 
 */
package com.tsa.puridiom.invoice.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 *
 */
public class InvoiceForwardBudgetServiceSetup extends Task
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		try
		{
			InvoiceHeader ivh = (InvoiceHeader) incomingRequest.get("invoiceHeader");

			incomingRequest.put("header", ivh);
			incomingRequest.put("lineItems", incomingRequest.get("invoiceLineList"));
			incomingRequest.put("formtype", "IVC");
			incomingRequest.put("budgetServiceAction", "FORWARD");

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "InvoiceForwardBudgetServiceSetup error " + e.getMessage());

			throw e;
		}

		return result;
	}
}
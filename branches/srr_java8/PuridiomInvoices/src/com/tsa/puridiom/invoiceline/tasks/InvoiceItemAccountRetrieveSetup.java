package com.tsa.puridiom.invoiceline.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import java.util.Map;

public class InvoiceItemAccountRetrieveSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String invoiceLine_icIvcLine = (String)incomingRequest.get("invoiceLine_icIvcLine");

			incomingRequest.put("InvoiceLine_icIvcLine",invoiceLine_icIvcLine);


			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			Log.debug(this, "Error on task InvoiceItemAccountRetrieveSetup: \r\n" + e.getMessage());
			this.status = Status.FAILED;
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
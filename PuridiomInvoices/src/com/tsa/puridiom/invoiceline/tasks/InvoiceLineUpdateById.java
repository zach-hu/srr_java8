package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvoiceLineUpdateById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvoiceLine invoiceLine = (InvoiceLine)incomingRequest.get("invoiceLine");
			if (invoiceLine == null)
			{
				throw new Exception ("InvoiceLine was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(invoiceLine);

			result = invoiceLine;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
package com.tsa.puridiom.invoiceheader.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvoiceHeaderUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvoiceHeader invoiceHeader = (InvoiceHeader)incomingRequest.get("invoiceHeader");
			if (invoiceHeader == null)
			{
				throw new Exception ("InvoiceHeader was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			dbs.update(invoiceHeader);

			result = invoiceHeader;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
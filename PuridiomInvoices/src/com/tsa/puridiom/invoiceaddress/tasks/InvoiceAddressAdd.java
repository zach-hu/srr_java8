package com.tsa.puridiom.invoiceaddress.tasks;

import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvoiceAddressAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvoiceAddress invoiceAddress = (InvoiceAddress)incomingRequest.get("invoiceAddress");
			if (invoiceAddress == null)
			{
				throw new Exception ("InvoiceAddress was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(invoiceAddress);

			result = invoiceAddress;
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
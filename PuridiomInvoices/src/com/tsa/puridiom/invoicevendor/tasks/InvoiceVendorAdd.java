package com.tsa.puridiom.invoicevendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvoiceVendorAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InvoiceVendor invoiceVendor = (InvoiceVendor)incomingRequest.get("invoiceVendor");
			if (invoiceVendor == null)
			{
				throw new Exception ("InvoiceVendor was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(invoiceVendor);
		
			result = invoiceVendor;
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
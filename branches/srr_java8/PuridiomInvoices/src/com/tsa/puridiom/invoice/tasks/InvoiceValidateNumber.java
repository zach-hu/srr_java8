package com.tsa.puridiom.invoice.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class InvoiceValidateNumber extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String invoiceNumber = (String) incomingRequest.get("InvoiceHeader_invoiceNumber");
			String vendorId = (String) incomingRequest.get("InvoiceHeader_vendorId");

			String queryString = "from InvoiceHeader as InvoiceHeader where InvoiceHeader.invoiceNumber = ? AND InvoiceHeader.vendorId = ? ";
			List resultList = dbs.query(queryString, new Object[] {invoiceNumber, vendorId } , new Type[] { Hibernate.STRING, Hibernate.STRING});

			if (resultList != null && resultList.size() > 0)
			{
				incomingRequest.put("isValidNumber", "FALSE");
			}
			else
			{
				incomingRequest.put("isValidNumber", "TRUE");
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}

}
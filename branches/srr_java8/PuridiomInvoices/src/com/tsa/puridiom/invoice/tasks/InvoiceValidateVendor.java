package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class InvoiceValidateVendor extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String invoiceNumber = HiltonUtility.ckNull((String) incomingRequest.get("InvoiceHeader_invoiceNumber"));
			String vendorId = HiltonUtility.ckNull((String) incomingRequest.get("InvoiceVendor_vendorId"));

			String queryString = "from InvoiceHeader as InvoiceHeader where InvoiceHeader.invoiceNumber = ? AND InvoiceHeader.vendorId = ? AND InvoiceHeader.invoiceNumber <> 'N/A' ";
			List resultList = dbs.query(queryString, new Object[] {invoiceNumber, vendorId } , new Type[] { Hibernate.STRING, Hibernate.STRING});

			if (resultList != null && resultList.size() > 0)
			{
				incomingRequest.put("isValidVendor", "FALSE");
			}
			else
			{
				incomingRequest.put("InvoiceHeader_vendorId", vendorId);
				incomingRequest.put("InvoiceAddress_vendorId", vendorId);
				incomingRequest.put("Vendor_vendorId", vendorId);
				incomingRequest.put("Address_addressType", vendorId);
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
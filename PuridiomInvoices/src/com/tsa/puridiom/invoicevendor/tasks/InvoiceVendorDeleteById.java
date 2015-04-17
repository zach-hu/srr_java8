package com.tsa.puridiom.invoicevendor.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvoiceVendorDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvoiceVendor invoiceVendor = (InvoiceVendor)incomingRequest.get("invoiceVendor");
		if(invoiceVendor == null)
		{
			invoiceVendor = new InvoiceVendor();
		}
		InvoiceVendorSetValuesPK setValues = new InvoiceVendorSetValuesPK();
		setValues.setValues(incomingRequest, invoiceVendor);
		dbs.delete(invoiceVendor);
		this.setStatus(dbs.getStatus()) ;
		return invoiceVendor ;
	}

}
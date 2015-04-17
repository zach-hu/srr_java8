package com.tsa.puridiom.invoiceheader.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvoiceHeaderDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvoiceHeader invoiceHeader = (InvoiceHeader)incomingRequest.get("invoiceHeader");
		if(invoiceHeader == null)
		{
			invoiceHeader = new InvoiceHeader();
		}
		InvoiceHeaderSetValuesPK setValues = new InvoiceHeaderSetValuesPK();
		setValues.setValues(incomingRequest, invoiceHeader);
		dbs.delete(invoiceHeader);
		this.setStatus(dbs.getStatus()) ;
		return invoiceHeader ;
	}

}
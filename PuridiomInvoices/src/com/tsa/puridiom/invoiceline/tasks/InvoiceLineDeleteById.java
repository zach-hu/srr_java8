package com.tsa.puridiom.invoiceline.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvoiceLineDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvoiceLine invoiceLine = (InvoiceLine)incomingRequest.get("invoiceLine");
		if(invoiceLine == null)
		{
			invoiceLine = new InvoiceLine();
		}
		InvoiceLineSetValuesPK setValues = new InvoiceLineSetValuesPK();
		setValues.setValues(incomingRequest, invoiceLine);
		dbs.delete(invoiceLine);
		this.setStatus(dbs.getStatus()) ;
		return invoiceLine ;
	}

}
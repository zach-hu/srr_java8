package com.tsa.puridiom.invoicevendor.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class InvoiceVendorRetrieveAllVendorNames extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

		List result = dbs.query("select consolidatedVendor.vendorName from ConsolidatedVendor as consolidatedVendor order by consolidatedVendor.vendorId ASC");

		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
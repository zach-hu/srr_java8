package com.tsa.puridiom.supplierportal.graphs.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class RfqBidVendorRetrieveByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icRfqHeader = (String) incomingRequest.get("RfqBidHistory_icHeader");
		if (icRfqHeader == null)
		{
			icRfqHeader = (String) incomingRequest.get("RfqBidHistory_icHeader");
		}
		StringBuffer queryString = new StringBuffer("from RfqVendor as rfqvendor where rfqvendor.id.icRfqHeader = '" + icRfqHeader + "'");

		queryString.append(" order by rfqvendor.id.vendorId ASC");

		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
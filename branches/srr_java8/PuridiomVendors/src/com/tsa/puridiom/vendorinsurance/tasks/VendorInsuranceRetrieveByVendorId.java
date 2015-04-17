package com.tsa.puridiom.vendorinsurance.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class VendorInsuranceRetrieveByVendorId extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String queryString = "from VendorInsurance as vendorinsurance where ";

		String vendorId = (String) incomingRequest.get("VendorInsurance_vendorId");
		queryString = queryString +  "vendorinsurance.vendorId = '" + vendorId + "'";

		List result = dbs.query(queryString) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
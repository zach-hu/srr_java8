package com.tsa.puridiom.vendorresponse.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class VendorResponseRetrieveByVendor extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String vendorId = (String) incomingRequest.get("VendorResponse_vendorId");
		String queryString = "from VendorResponse as vendorresponse where vendorresponse.id.vendorId = '" + vendorId + "'";
		List result = dbs.query(queryString.toString()) ;

		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
package com.tsa.puridiom.vendorregcommrel.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.util.List;
import java.util.Map;

public class VendorRegCommRelRetrieveByVendor extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from VendorRegCommRel as vendorregcommrel where 1=1 ");

		String vendorId = (String) incomingRequest.get("VendorRegCommRel_vendorId");
		if (Utility.isEmpty(vendorId)) {
		    throw new Exception ("Vendor Id cannot be empty for VendorRegCommRelRetrieveByVendor");
		}
		
		queryString.append(" AND vendorregcommrel.id.vendorId = '" + vendorId + "'");

		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
package com.tsa.puridiom.vendoraffiliate.tasks;

import com.tsagate.foundation.database.DBSession;

import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class VendorAffiliateRetrieveByVendorId extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from VendorAffiliate as vendoraffiliate where 1=1 ");
		if(incomingRequest.containsKey("VendorAffiliate_vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("VendorAffiliate_vendorId");
			queryString.append(" AND vendoraffiliate.id.vendorId = '" + vendorId + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
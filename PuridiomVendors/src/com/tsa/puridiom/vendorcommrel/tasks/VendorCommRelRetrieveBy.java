package com.tsa.puridiom.vendorcommrel.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class VendorCommRelRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from VendorCommRel as vendorcommrel where 1=1 ");
		if(incomingRequest.containsKey("VendorCommRel_vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("VendorCommRel_vendorId");
			queryString.append(" AND vendorcommrel.id.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("VendorCommRel_commodityCode"))
		{			
			String commodityCode = (String) incomingRequest.get("VendorCommRel_commodityCode");
			queryString.append(" AND vendorcommrel.id.commodityCode = '" + commodityCode + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
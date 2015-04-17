package com.tsa.puridiom.invvendor.tasks;
import java.util.List;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class InvVendorRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvVendor as invvendor where 1=1 ");
		if(incomingRequest.containsKey("InvVendor.itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("InvVendor.itemNumber");
			queryString.append(" AND invvendor.id.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvVendor.vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("InvVendor.vendorId");
			queryString.append(" AND invvendor.id.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("InvVendor.lastDate"))
		{			
			String lastDate = (String) incomingRequest.get("InvVendor.lastDate");
			queryString.append(" AND invvendor.lastDate = '" + lastDate + "'");
		}
		if(incomingRequest.containsKey("InvVendor.lastPrice"))
		{			
			String lastPrice = (String) incomingRequest.get("InvVendor.lastPrice");
			queryString.append(" AND invvendor.lastPrice = '" + lastPrice + "'");
		}
		if(incomingRequest.containsKey("InvVendor.mfgNumber"))
		{			
			String mfgNumber = (String) incomingRequest.get("InvVendor.mfgNumber");
			queryString.append(" AND invvendor.mfgNumber = '" + mfgNumber + "'");
		}
		if(incomingRequest.containsKey("InvVendor.leadTime"))
		{			
			String leadTime = (String) incomingRequest.get("InvVendor.leadTime");
			queryString.append(" AND invvendor.leadTime = '" + leadTime + "'");
		}
		if(incomingRequest.containsKey("InvVendor.primaryVendor"))
		{			
			String primaryVendor = (String) incomingRequest.get("InvVendor.primaryVendor");
			queryString.append(" AND invvendor.primaryVendor = '" + primaryVendor + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
package com.tsa.puridiom.invlocation.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class InvLocationRetrieveByInvItem extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvLocation as invlocation where 1=1 ");
		if(incomingRequest.containsKey("InvItem_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("InvItem_itemNumber");
			queryString.append(" AND invlocation.id.itemNumber = '" + itemNumber + "'");
		}	
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
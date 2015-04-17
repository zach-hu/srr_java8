package com.tsa.puridiom.invalternate.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvAlternateRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvAlternate as invalternate where 1=1 ");
		if(incomingRequest.containsKey("InvAlternate_icAlternate"))
		{			
			String icAlternate = (String) incomingRequest.get("InvAlternate_icAlternate");
			queryString.append(" AND invalternate.id.icAlternate = '" + icAlternate + "'");
		}
		if(incomingRequest.containsKey("InvAlternate_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("InvAlternate_itemNumber");
			queryString.append(" AND invalternate.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvAlternate_altItemNumber"))
		{			
			String altItemNumber = (String) incomingRequest.get("InvAlternate_altItemNumber");
			queryString.append(" AND invalternate.altItemNumber = '" + altItemNumber + "'");
		}
		if(incomingRequest.containsKey("InvAlternate_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("InvAlternate_dateEntered");
			queryString.append(" AND invalternate.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InvAlternate_owner"))
		{			
			String owner = (String) incomingRequest.get("InvAlternate_owner");
			queryString.append(" AND invalternate.owner = '" + owner + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
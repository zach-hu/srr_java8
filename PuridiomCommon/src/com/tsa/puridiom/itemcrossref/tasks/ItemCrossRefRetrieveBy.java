package com.tsa.puridiom.itemcrossref.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class ItemCrossRefRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from ItemCrossRef as itemcrossref where 1=1 ");
		if(incomingRequest.containsKey("ItemCrossRef_altItemNumber"))
		{			
			String altItemNumber = (String) incomingRequest.get("ItemCrossRef_altItemNumber");
			queryString.append(" AND itemcrossref.id.altItemNumber = '" + altItemNumber + "'");
		}
		if(incomingRequest.containsKey("ItemCrossRef_description"))
		{			
			String description = (String) incomingRequest.get("ItemCrossRef_description");
			queryString.append(" AND itemcrossref.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("ItemCrossRef_itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("ItemCrossRef_itemNumber");
			queryString.append(" AND itemcrossref.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("ItemCrossRef_source"))
		{			
			String source = (String) incomingRequest.get("ItemCrossRef_source");
			queryString.append(" AND itemcrossref.source = '" + source + "'");
		}
		if(incomingRequest.containsKey("ItemCrossRef_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("ItemCrossRef_dateEntered");
			queryString.append(" AND itemcrossref.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("ItemCrossRef_dateExpires"))
		{			
			String dateExpires = (String) incomingRequest.get("ItemCrossRef_dateExpires");
			queryString.append(" AND itemcrossref.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("ItemCrossRef_status"))
		{			
			String status = (String) incomingRequest.get("ItemCrossRef_status");
			queryString.append(" AND itemcrossref.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("ItemCrossRef_owner"))
		{			
			String owner = (String) incomingRequest.get("ItemCrossRef_owner");
			queryString.append(" AND itemcrossref.owner = '" + owner + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
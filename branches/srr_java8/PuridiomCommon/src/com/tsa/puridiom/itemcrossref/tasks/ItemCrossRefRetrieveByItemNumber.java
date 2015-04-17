package com.tsa.puridiom.itemcrossref.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class ItemCrossRefRetrieveByItemNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from ItemCrossRef as itemcrossref where 1=1 ");
		String itemNumber = (String) incomingRequest.get("ItemCrossRef_itemNumber");
		if (itemNumber == null) {
			itemNumber = (String) incomingRequest.get("InvItem_itemNumber") ;
		}
		queryString.append(" AND itemcrossref.itemNumber = '" + itemNumber + "'");

		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
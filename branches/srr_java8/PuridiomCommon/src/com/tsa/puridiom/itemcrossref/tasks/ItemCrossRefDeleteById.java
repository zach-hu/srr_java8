package com.tsa.puridiom.itemcrossref.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ItemCrossRefDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		ItemCrossRef itemCrossRef = (ItemCrossRef)incomingRequest.get("itemCrossRef");
		if (itemCrossRef == null)
		{
			itemCrossRef = new ItemCrossRef();
		}
		dbs.delete(itemCrossRef);
		this.setStatus(dbs.getStatus()) ;
		return itemCrossRef ;
	}

}
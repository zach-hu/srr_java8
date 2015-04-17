package com.tsa.puridiom.recentorderitem.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RecentOrderItemDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RecentOrderItem recentOrderItem = (RecentOrderItem)incomingRequest.get("recentOrderItem");
		if(recentOrderItem == null)
		{
			recentOrderItem = new RecentOrderItem();
		}
		RecentOrderItemSetValuesPK setValues = new RecentOrderItemSetValuesPK();
		setValues.setValues(incomingRequest, recentOrderItem);
		dbs.delete(recentOrderItem);
		this.setStatus(dbs.getStatus()) ;
		return recentOrderItem ;
	}

}
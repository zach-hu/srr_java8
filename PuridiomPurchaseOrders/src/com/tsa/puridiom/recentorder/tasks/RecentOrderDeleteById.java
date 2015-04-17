package com.tsa.puridiom.recentorder.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RecentOrderDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RecentOrder recentOrder = (RecentOrder)incomingRequest.get("recentOrder");
		if(recentOrder == null)
		{
			recentOrder = new RecentOrder();
		}
		RecentOrderSetValuesPK setValues = new RecentOrderSetValuesPK();
		setValues.setValues(incomingRequest, recentOrder);
		dbs.delete(recentOrder);
		this.setStatus(dbs.getStatus()) ;
		return recentOrder ;
	}

}
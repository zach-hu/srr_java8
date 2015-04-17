package com.tsa.puridiom.recentreqitem.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RecentReqItemDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RecentReqItem recentReqItem = (RecentReqItem)incomingRequest.get("recentReqItem");
		if(recentReqItem == null)
		{
			recentReqItem = new RecentReqItem();
		}
		RecentReqItemSetValuesPK setValues = new RecentReqItemSetValuesPK();
		setValues.setValues(incomingRequest, recentReqItem);
		dbs.delete(recentReqItem);
		this.setStatus(dbs.getStatus()) ;
		return recentReqItem ;
	}

}
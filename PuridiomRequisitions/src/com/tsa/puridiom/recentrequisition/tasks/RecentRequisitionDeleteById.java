package com.tsa.puridiom.recentrequisition.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RecentRequisitionDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RecentRequisition recentRequisition = (RecentRequisition)incomingRequest.get("recentRequisition");
		if(recentRequisition == null)
		{
			recentRequisition = new RecentRequisition();
		}
		RecentRequisitionSetValuesPK setValues = new RecentRequisitionSetValuesPK();
		setValues.setValues(incomingRequest, recentRequisition);
		dbs.delete(recentRequisition);
		this.setStatus(dbs.getStatus()) ;
		return recentRequisition ;
	}

}
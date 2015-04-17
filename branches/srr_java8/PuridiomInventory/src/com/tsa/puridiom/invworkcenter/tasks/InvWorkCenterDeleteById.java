package com.tsa.puridiom.invworkcenter.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvWorkCenterDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvWorkCenter invWorkCenter = (InvWorkCenter)incomingRequest.get("invWorkCenter");
		if(invWorkCenter == null)
		{
			invWorkCenter = new InvWorkCenter();
		}
		InvWorkCenterSetValuesPK setValues = new InvWorkCenterSetValuesPK();
		setValues.setValues(incomingRequest, invWorkCenter);
		dbs.delete(invWorkCenter);
		this.setStatus(dbs.getStatus()) ;
		return invWorkCenter ;
	}

}
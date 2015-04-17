package com.tsa.puridiom.invtask.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvTaskDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvTask invTask = (InvTask)incomingRequest.get("invTask");
		if(invTask == null)
		{
			invTask = new InvTask();
		}
		InvTaskSetValuesPK setValues = new InvTaskSetValuesPK();
		setValues.setValues(incomingRequest, invTask);
		dbs.delete(invTask);
		this.setStatus(dbs.getStatus()) ;
		return invTask ;
	}

}
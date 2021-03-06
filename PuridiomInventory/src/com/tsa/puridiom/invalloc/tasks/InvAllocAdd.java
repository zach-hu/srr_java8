package com.tsa.puridiom.invalloc.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvAllocAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		InvAlloc invAlloc = (InvAlloc)incomingRequest.get("invAlloc");
		if(invAlloc == null)
		{
			invAlloc = new InvAlloc();
		}
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvAllocSetValues setValues = new InvAllocSetValues();
		setValues.setValues(incomingRequest, invAlloc);
		dbs.add(invAlloc);
		this.setStatus(dbs.getStatus()) ;
		return invAlloc;
	}

}
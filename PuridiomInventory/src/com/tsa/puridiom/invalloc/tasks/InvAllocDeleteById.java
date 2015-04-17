package com.tsa.puridiom.invalloc.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class InvAllocDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvAlloc invAlloc = (InvAlloc)incomingRequest.get("invAlloc");
		if(invAlloc == null)
		{
			invAlloc = new InvAlloc();
		}
		InvAllocSetValuesPK setValues = new InvAllocSetValuesPK();
		setValues.setValues(incomingRequest, invAlloc);
		dbs.delete(invAlloc);
		this.setStatus(dbs.getStatus()) ;
		return invAlloc ;
	}

}
package com.tsa.puridiom.invreturn.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvReturn;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;


public class InvReturnDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvReturn invReturn = (InvReturn)incomingRequest.get("invReturn");
		if(invReturn == null)
		{
			invReturn = new InvReturn();
		}
		InvReturnSetValuesPK setValues = new InvReturnSetValuesPK();
		setValues.setValues(incomingRequest, invReturn);
		dbs.delete(invReturn);
		this.setStatus(dbs.getStatus()) ;
		return invReturn ;
	}

}
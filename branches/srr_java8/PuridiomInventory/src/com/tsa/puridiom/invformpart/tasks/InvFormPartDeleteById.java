package com.tsa.puridiom.invformpart.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.InvFormPart;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class InvFormPartDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvFormPart invFormPart = (InvFormPart)incomingRequest.get("invFormPart");
		if(invFormPart == null)
		{
			invFormPart = new InvFormPart();
		}
		InvFormPartSetValuesPK setValues = new InvFormPartSetValuesPK();
		setValues.setValues(incomingRequest, invFormPart);
		dbs.delete(invFormPart);
		this.setStatus(dbs.getStatus()) ;
		return invFormPart ;
	}

}
package com.tsa.puridiom.invmethod.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvMethodDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvMethod invMethod = (InvMethod)incomingRequest.get("invMethod");
		if(invMethod == null)
		{
			invMethod = new InvMethod();
		}
		InvMethodSetValuesPK setValues = new InvMethodSetValuesPK();
		setValues.setValues(incomingRequest, invMethod);
		dbs.delete(invMethod);
		this.setStatus(dbs.getStatus()) ;
		return invMethod ;
	}

}
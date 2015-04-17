package com.tsa.puridiom.invproperty.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvPropertyDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvProperty invProperty = (InvProperty)incomingRequest.get("invProperty");
		if(invProperty == null)
		{
			invProperty = new InvProperty();
		}
		InvPropertySetValuesPK setValues = new InvPropertySetValuesPK();
		setValues.setValues(incomingRequest, invProperty);
		dbs.delete(invProperty);
		this.setStatus(dbs.getStatus()) ;
		return invProperty ;
	}

}
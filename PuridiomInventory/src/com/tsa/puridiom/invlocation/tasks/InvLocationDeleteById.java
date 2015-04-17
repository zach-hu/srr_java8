package com.tsa.puridiom.invlocation.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class InvLocationDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvLocation invLocation = (InvLocation)incomingRequest.get("invLocation");
		if(invLocation == null)
		{
			invLocation = new InvLocation();
		}
		InvLocationSetValuesPK setValues = new InvLocationSetValuesPK();
		setValues.setValues(incomingRequest, invLocation);
		dbs.delete(invLocation);
		this.setStatus(dbs.getStatus()) ;
		return invLocation ;
	}

}
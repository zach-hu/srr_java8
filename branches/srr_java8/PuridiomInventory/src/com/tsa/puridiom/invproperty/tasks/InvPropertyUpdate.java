package com.tsa.puridiom.invproperty.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvPropertyUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InvProperty invProperty = (InvProperty)incomingRequest.get("invProperty");
			if (invProperty == null)
			{
				throw new Exception ("InvProperty was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(invProperty);
		
			result = invProperty;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
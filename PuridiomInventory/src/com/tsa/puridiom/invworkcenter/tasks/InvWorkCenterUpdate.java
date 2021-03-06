package com.tsa.puridiom.invworkcenter.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvWorkCenterUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InvWorkCenter invWorkCenter = (InvWorkCenter)incomingRequest.get("invWorkCenter");
			if (invWorkCenter == null)
			{
				throw new Exception ("InvWorkCenter was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(invWorkCenter);
		
			result = invWorkCenter;
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
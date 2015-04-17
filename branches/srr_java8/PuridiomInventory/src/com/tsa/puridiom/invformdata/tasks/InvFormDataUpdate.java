package com.tsa.puridiom.invformdata.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.InvFormData;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvFormDataUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InvFormData invFormData = (InvFormData)incomingRequest.get("invFormData");
			if (invFormData == null)
			{
				throw new Exception ("InvFormData was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(invFormData);
		
			result = invFormData;
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
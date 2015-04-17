package com.tsa.puridiom.checklistresponse.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ChecklistResponseUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			ChecklistResponse checklistResponse = (ChecklistResponse)incomingRequest.get("checklistResponse");
			if (checklistResponse == null)
			{
				throw new Exception ("ChecklistResponse was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(checklistResponse);
		
			result = checklistResponse;
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
package com.tsa.puridiom.checklistentry.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ChecklistEntryAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			ChecklistEntry checklistEntry = (ChecklistEntry)incomingRequest.get("checklistEntry");
			if (checklistEntry == null)
			{
				throw new Exception ("ChecklistEntry was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(checklistEntry);
		
			result = checklistEntry;
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
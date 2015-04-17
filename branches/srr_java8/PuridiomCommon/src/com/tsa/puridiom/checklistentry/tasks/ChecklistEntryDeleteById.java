package com.tsa.puridiom.checklistentry.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ChecklistEntryDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		ChecklistEntry checklistEntry = (ChecklistEntry)incomingRequest.get("checklistEntry");
		if(checklistEntry == null)
		{
			checklistEntry = new ChecklistEntry();
		}
		ChecklistEntrySetValuesPK setValues = new ChecklistEntrySetValuesPK();
		setValues.setValues(incomingRequest, checklistEntry);
		dbs.delete(checklistEntry);
		this.setStatus(dbs.getStatus()) ;
		return checklistEntry ;
	}

}
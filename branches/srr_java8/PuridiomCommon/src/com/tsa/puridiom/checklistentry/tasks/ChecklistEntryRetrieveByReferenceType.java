package com.tsa.puridiom.checklistentry.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ChecklistEntryRetrieveByReferenceType extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		if(incomingRequest.containsKey("ChecklistEntry_referenceType"))
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String referenceType = (String) incomingRequest.get("ChecklistEntry_referenceType");
			String queryString = "from ChecklistEntry as checklistentry where checklistentry.referenceType = '" + referenceType + "' order by checklistentry.sequence";

			result = dbs.query(queryString) ;
			this.setStatus(dbs.getStatus()) ;
		}

		return result ;
	}
}
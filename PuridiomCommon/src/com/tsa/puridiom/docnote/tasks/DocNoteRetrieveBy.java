package com.tsa.puridiom.docnote.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class DocNoteRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from DocNote as docnote where 1=1 ");
		if(incomingRequest.containsKey("DocNote_icNotes"))
		{			
			String icNotes = (String) incomingRequest.get("DocNote_icNotes");
			queryString.append(" AND docnote.id.icNotes = '" + icNotes + "'");
		}
		if(incomingRequest.containsKey("DocNote_notes"))
		{			
			String notes = (String) incomingRequest.get("DocNote_notes");
			queryString.append(" AND docnote.notes = '" + notes + "'");
		}
		if(incomingRequest.containsKey("DocNote_referenceType"))
		{			
			String referenceType = (String) incomingRequest.get("DocNote_referenceType");
			queryString.append(" AND docnote.referenceType = '" + referenceType + "'");
		}
		if(incomingRequest.containsKey("DocNote_idReference"))
		{			
			String idReference = (String) incomingRequest.get("DocNote_idReference");
			queryString.append(" AND docnote.idReference = '" + idReference + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
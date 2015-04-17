package com.tsa.puridiom.stddocument.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class StdDocumentRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from StdDocument as stddocument where 1=1 ");
		if(incomingRequest.containsKey("StdDocument_fileName"))
		{			
			String fileName = (String) incomingRequest.get("StdDocument_fileName");
			queryString.append(" AND stddocument.id.fileName = '" + fileName + "'");
		}
		if(incomingRequest.containsKey("StdDocument_title"))
		{			
			String title = (String) incomingRequest.get("StdDocument_title");
			queryString.append(" AND stddocument.id.title = '" + title + "'");
		}
		if(incomingRequest.containsKey("StdDocument_description"))
		{			
			String description = (String) incomingRequest.get("StdDocument_description");
			queryString.append(" AND stddocument.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("StdDocument_docType"))
		{			
			String docType = (String) incomingRequest.get("StdDocument_docType");
			queryString.append(" AND stddocument.docType = '" + docType + "'");
		}
		if(incomingRequest.containsKey("StdDocument_datePosted"))
		{			
			String datePosted = (String) incomingRequest.get("StdDocument_datePosted");
			queryString.append(" AND stddocument.datePosted = '" + datePosted + "'");
		}
		if(incomingRequest.containsKey("StdDocument_hits"))
		{			
			String hits = (String) incomingRequest.get("StdDocument_hits");
			queryString.append(" AND stddocument.hits = '" + hits + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
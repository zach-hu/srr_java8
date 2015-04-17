package com.tsa.puridiom.stddocument.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class StdDocumentDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StdDocument stdDocument = (StdDocument)incomingRequest.get("stdDocument");
		if(stdDocument == null)
		{
			stdDocument = new StdDocument();
		}
		StdDocumentSetValuesPK setValues = new StdDocumentSetValuesPK();
		setValues.setValues(incomingRequest, stdDocument);
		dbs.delete(stdDocument);
		this.setStatus(dbs.getStatus()) ;
		return stdDocument ;
	}

}
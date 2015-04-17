package com.tsa.puridiom.docnote.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class DocNoteDelete extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		DocNote docNote = (DocNote)incomingRequest.get("docNote");
		if(docNote == null)
		{
			docNote = new DocNote();
		}
		DocNoteSetValuesPK setValues = new DocNoteSetValuesPK();
		setValues.setValues(incomingRequest, docNote);
		dbs.delete(docNote);
		this.setStatus(dbs.getStatus()) ;
		return docNote ;
	}

}
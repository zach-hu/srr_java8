package com.tsa.puridiom.docattachment.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class DocAttachmentDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		DocAttachment docAttachment = (DocAttachment)incomingRequest.get("docAttachment");
		if(docAttachment == null)
		{
			docAttachment = new DocAttachment();
			DocAttachmentSetValuesPK setValues = new DocAttachmentSetValuesPK();
			setValues.setValues(incomingRequest, docAttachment);
		}
		dbs.delete(docAttachment);
		this.setStatus(dbs.getStatus()) ;
		return docAttachment ;
	}

}
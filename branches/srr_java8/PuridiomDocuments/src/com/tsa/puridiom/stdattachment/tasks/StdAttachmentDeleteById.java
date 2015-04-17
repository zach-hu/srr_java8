package com.tsa.puridiom.stdattachment.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class StdAttachmentDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StdAttachment stdAttachment = (StdAttachment)incomingRequest.get("stdAttachment");
		
		if (stdAttachment == null) {
			stdAttachment = new StdAttachment();
		}
		StdAttachmentSetValues setValues = new StdAttachmentSetValues();
		stdAttachment = (StdAttachment) setValues.executeTask(incomingRequest);
		
		dbs.delete(stdAttachment);
		this.setStatus(dbs.getStatus()) ;
		return stdAttachment ;
	}

}
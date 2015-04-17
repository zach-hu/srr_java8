package com.tsa.puridiom.docattachment.tasks;

import com.tsa.puridiom.entity.DocAttachment;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class DocAttachmentDeleteByIdSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    DocAttachment docAttachment = (DocAttachment) incomingRequest.get("docAttachment");

		    incomingRequest.put("DocAttachment_docFilename", docAttachment.getDocFilename());
		    incomingRequest.put("filename", docAttachment.getDocFilename());
		    
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
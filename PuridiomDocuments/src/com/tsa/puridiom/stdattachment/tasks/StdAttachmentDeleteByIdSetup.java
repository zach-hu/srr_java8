package com.tsa.puridiom.stdattachment.tasks;

import com.tsa.puridiom.entity.StdAttachment;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class StdAttachmentDeleteByIdSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    StdAttachment stdAttachment = (StdAttachment) incomingRequest.get("stdAttachment");

		    if (stdAttachment != null) {
			    incomingRequest.put("StdAttachment_docFilename", stdAttachment.getDocFilename());
			    incomingRequest.put("filename", stdAttachment.getDocFilename());
		    }
	        incomingRequest.put("documentType", "stdform");
		    
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
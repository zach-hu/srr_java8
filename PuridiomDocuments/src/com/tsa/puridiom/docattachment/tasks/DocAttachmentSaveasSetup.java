package com.tsa.puridiom.docattachment.tasks;

import com.tsa.puridiom.entity.DocAttachment;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.util.Map;

public class DocAttachmentSaveasSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    DocAttachment docAttachment = (DocAttachment) incomingRequest.get("docAttachment");
		    
		    UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		    incomingRequest.put("newDocAttachment_docIc",ukg.getUniqueKey().toString());
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
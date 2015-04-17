package com.tsa.puridiom.stdattachment.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.util.Map;

public class StdAttachmentAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		    incomingRequest.put("StdAttachment_docIc",ukg.getUniqueKey().toString());
		    
			if (!incomingRequest.containsKey("StdAttachment_docTitle")) {
			    incomingRequest.put("StdAttachment_docTitle", "");
			}
			if (!incomingRequest.containsKey("StdAttachment_docFilename")) {
			    incomingRequest.put("StdAttachment_docFilename", "");
			}
			if (!incomingRequest.containsKey("StdAttachment_docPrint")) {
			    incomingRequest.put("StdAttachment_docPrint", "N");
			}

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
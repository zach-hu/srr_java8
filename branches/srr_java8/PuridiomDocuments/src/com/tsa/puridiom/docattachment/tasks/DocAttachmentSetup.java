package com.tsa.puridiom.docattachment.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.util.Map;

public class DocAttachmentSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("DocAttachment_icHeader", "0");
			incomingRequest.put("DocAttachment_docIc","0");
			incomingRequest.put("DocAttachment_docSource", "");
			incomingRequest.put("DocAttachment_docTitle", "");
			incomingRequest.put("DocAttachment_docFilename", "");
			incomingRequest.put("DocAttachment_docPrint", "");
			incomingRequest.put("DocAttachment_docPost", "");

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
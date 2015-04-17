package com.tsa.puridiom.docattachment.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.util.Map;

public class DocAttachmentSetValuesFromStdAttachment extends Task {
    
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			StdAttachment stdAttachment = (StdAttachment) incomingRequest.get("stdAttachment");
			
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		    incomingRequest.put("DocAttachment_docIc",ukg.getUniqueKey().toString());
		    incomingRequest.put("DocAttachment_docTitle", stdAttachment.getDocTitle());
			incomingRequest.put("DocAttachment_docPrint", stdAttachment.getDocPrint());
		    incomingRequest.put("filename", stdAttachment.getDocFilename());
		    incomingRequest.put("originalDocType", "stdform");
		    incomingRequest.put("newDocType", "internal");

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
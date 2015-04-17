package com.tsa.puridiom.stdattachment.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class StdAttachmentSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain stdAttachment */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			StdAttachment	originalStdAttachment = (StdAttachment) incomingRequest.get("stdAttachment");
			StdAttachment	stdAttachment = new StdAttachment();

			stdAttachment.setDocIc(originalStdAttachment.getDocIc());
			stdAttachment.setDocTitle(originalStdAttachment.getDocTitle());
			stdAttachment.setDocFilename(originalStdAttachment.getDocFilename());
			stdAttachment.setDocPrint(originalStdAttachment.getDocPrint());

			incomingRequest.put("stdAttachment", stdAttachment);

			StdAttachmentAdd addTask = new StdAttachmentAdd();
			stdAttachment = (StdAttachment) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = stdAttachment;
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
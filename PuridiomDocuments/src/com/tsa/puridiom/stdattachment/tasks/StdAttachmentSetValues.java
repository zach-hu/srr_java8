package com.tsa.puridiom.stdattachment.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class StdAttachmentSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			StdAttachment stdAttachment = (StdAttachment) incomingRequest.get("stdAttachment");
			if (stdAttachment == null)
			{
				stdAttachment = new StdAttachment();
			}

			if (incomingRequest.containsKey("StdAttachment_docIc"))
			{
				String docIcString = (String) incomingRequest.get("StdAttachment_docIc");
				if (Utility.isEmpty(docIcString))
				{
					docIcString = "0";
				}
				BigDecimal docIc = new BigDecimal ( docIcString );
				stdAttachment.setDocIc(docIc);
			}
			if (incomingRequest.containsKey("StdAttachment_docTitle"))
			{
				String docTitle = (String) incomingRequest.get("StdAttachment_docTitle");
				stdAttachment.setDocTitle(docTitle);
			}
			if (incomingRequest.containsKey("StdAttachment_docFilename"))
			{
				String docFilename = (String) incomingRequest.get("StdAttachment_docFilename");
				stdAttachment.setDocFilename(docFilename);
			}
			if (incomingRequest.containsKey("StdAttachment_docPrint"))
			{
				String docPrint = (String) incomingRequest.get("StdAttachment_docPrint");
				stdAttachment.setDocPrint(docPrint);
			}

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
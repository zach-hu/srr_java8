package com.tsa.puridiom.doctext.tasks;

import com.tsa.puridiom.entity.DocText;
import com.tsa.puridiom.entity.StdComment;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.Map;

public class DocTextSetValuesFromStdComment extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			StdComment stdComment = (StdComment) incomingRequest.get("stdComment");
			String	commentPublic = stdComment.getCommentPublic();
			
			DocText docText = (DocText) incomingRequest.get("docText");
			
			if (commentPublic.equalsIgnoreCase("N"))
			{
				incomingRequest.put("DocText_referenceType", "STD");
			}
			else
			{
				incomingRequest.put("DocText_referenceType", incomingRequest.get("Default_referenceType"));
			}
			
			incomingRequest.put("DocText_icText", (String) incomingRequest.get("DocComment_icText"));
			incomingRequest.put("DocText_stdText", docText.getStdText());
			
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
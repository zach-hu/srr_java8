package com.tsa.puridiom.doctext.tasks;

import com.tsa.puridiom.entity.StdComment;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class DocTextGetIcTextFromStdComment extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = "";

		try
		{
			StdComment stdComment = (StdComment) incomingRequest.get("stdComment");
			if(stdComment != null)
			{
				String	icText = stdComment.getIcText().toString();

				incomingRequest.put("DocText_icText", icText);

				result = icText;
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
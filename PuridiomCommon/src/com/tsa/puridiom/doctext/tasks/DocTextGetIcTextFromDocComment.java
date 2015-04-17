package com.tsa.puridiom.doctext.tasks;

import com.tsa.puridiom.entity.DocComment;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class DocTextGetIcTextFromDocComment extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DocComment docComment = (DocComment) incomingRequest.get("docComment");
			String	icText = docComment.getIcText().toString();

			incomingRequest.put("DocText_icText", icText);

			result = icText;

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
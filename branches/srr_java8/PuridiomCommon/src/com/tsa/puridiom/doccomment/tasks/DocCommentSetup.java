package com.tsa.puridiom.doccomment.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class DocCommentSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("DocComment_icHeader", "0");
			incomingRequest.put("DocComment_icLine", "0");
			incomingRequest.put("DocComment_commentOrder", "0");
			incomingRequest.put("DocComment_commentId", "");
			incomingRequest.put("DocComment_commentTitle", "");
			incomingRequest.put("DocComment_icText", "0");
			incomingRequest.put("DocComment_commentPrint", "");
			incomingRequest.put("DocComment_commentPlace", "");
			incomingRequest.put("DocComment_commentSource", "");
			incomingRequest.put("DocComment_commentBold", "");
			incomingRequest.put("DocComment_commentPublic", "");
			incomingRequest.put("DocComment_commentWebpost", "");

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
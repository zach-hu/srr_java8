package com.tsa.puridiom.timer;

import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;

public class EmailFYIHtmlApprovalsJob extends EmailHtmlApprovalsJob
{
	protected void setAction(Map incomingRequest)
	{
		incomingRequest.put("SendQueue_action", SendQueue.HTML_FYI_EMAIL_ACTION);
	}


}

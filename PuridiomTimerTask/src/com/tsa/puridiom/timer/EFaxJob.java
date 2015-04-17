package com.tsa.puridiom.timer;

import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;

public class EFaxJob extends PdfsJob
{
	public String getJobType() {
		return "efax";
	}

	protected void setAction(Map incomingRequest)
	{
		incomingRequest.put("SendQueue_action", SendQueue.E_FAX_ACTION);
	}
}

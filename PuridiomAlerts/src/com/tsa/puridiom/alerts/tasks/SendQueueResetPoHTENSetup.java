package com.tsa.puridiom.alerts.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class SendQueueResetPoHTENSetup extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			incomingRequest.put("newStatus", DocumentStatus.PO_APPROVING);
			String icHeader = (String)incomingRequest.get("SendQueue_docic");
			incomingRequest.put("PoHeader_icPoHeader", icHeader);
			incomingRequest.put("ApprovalLog_icHeader", icHeader);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return super.executeTask(object);
	}


}

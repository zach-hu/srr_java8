package com.tsa.puridiom.sendqueue.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class SendQueueSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("SendQueue_queueid", "0");
			incomingRequest.put("SendQueue_doctype", "");
			incomingRequest.put("SendQueue_docic", "0");
			incomingRequest.put("SendQueue_subject", "");
			incomingRequest.put("SendQueue_messagetext", "");
			incomingRequest.put("SendQueue_owner", "");
			incomingRequest.put("SendQueue_sendfromtype", "");
			incomingRequest.put("SendQueue_sendfrom", "");
			incomingRequest.put("SendQueue_sendtotype", "");
			incomingRequest.put("SendQueue_sendto", "");
			incomingRequest.put("SendQueue_status", "");
			incomingRequest.put("SendQueue_dateadded", "");
			incomingRequest.put("SendQueue_timeadded", "");
			incomingRequest.put("SendQueue_action", "");
			incomingRequest.put("SendQueue_datesent", "");
			incomingRequest.put("SendQueue_timesent", "");
			incomingRequest.put("SendQueue_attachment", "");
			incomingRequest.put("SendQueue_vendorId", "");
			incomingRequest.put("SendQueue_attempts", "0");
			incomingRequest.put("SendQueue_errorText", "");

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
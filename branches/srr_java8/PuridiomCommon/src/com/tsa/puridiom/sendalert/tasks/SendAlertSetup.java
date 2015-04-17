package com.tsa.puridiom.sendalert.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class SendAlertSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("SendAlert_queueid", "0");
			incomingRequest.put("SendAlert_doctype", "");
			incomingRequest.put("SendAlert_docic", "0");
			incomingRequest.put("SendAlert_subject", "");
			incomingRequest.put("SendAlert_messagetext", "");
			incomingRequest.put("SendAlert_owner", "");
			incomingRequest.put("SendAlert_sendfromtype", "");
			incomingRequest.put("SendAlert_sendfrom", "");
			incomingRequest.put("SendAlert_sendtotype", "");
			incomingRequest.put("SendAlert_sendto", "");
			incomingRequest.put("SendAlert_status", "");
			incomingRequest.put("SendAlert_dateadded", "");
			incomingRequest.put("SendAlert_timeadded", "");
			incomingRequest.put("SendAlert_action", "");
			incomingRequest.put("SendAlert_datesent", "");
			incomingRequest.put("SendAlert_timesent", "");
			incomingRequest.put("SendAlert_attachment", "");
			incomingRequest.put("SendAlert_vendorId", "");
			incomingRequest.put("SendAlert_attempts", "0");
			incomingRequest.put("SendAlert_errorText", "");

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
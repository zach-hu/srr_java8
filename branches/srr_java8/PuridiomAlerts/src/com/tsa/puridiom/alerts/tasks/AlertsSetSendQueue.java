package com.tsa.puridiom.alerts.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class AlertsSetSendQueue extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		try
		{
			SendQueue sendQueue = (SendQueue)incomingRequest.get("alertSendQueue");

			sendQueue.setStatus("10");
			sendQueue.setErrorText("Alert sucessfully sent");
			sendQueue.setAttempts(sendQueue.getAttempts().add(new BigDecimal(1)));
			sendQueue.setDatesent(Dates.today("yyyy/MM/dd", ""));
			sendQueue.setTimesent(Dates.getNow(null, ""));

			if ( incomingRequest.containsKey("report") && !Utility.isEmpty((String) incomingRequest.get("report")) )
			{
				//sendQueue.setAttachment((String) incomingRequest.get("report"));
				incomingRequest.put("SendQueue_attachment", incomingRequest.get("report"));
			}

			ret = sendQueue;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("AlertGetProcessFromType failed!" + e.getMessage(), e);
		}
		return ret;
	}
}

package com.tsa.puridiom.sendalert.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class SendAlertSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain sendQueue */
			SendAlert	originalSendAlert = (SendAlert) incomingRequest.get("sendAlert");
			SendAlert	sendAlert = new SendAlert();

			sendAlert.setQueueid(originalSendAlert.getQueueid());
			sendAlert.setDoctype(originalSendAlert.getDoctype());
			sendAlert.setDocic(originalSendAlert.getDocic());
			sendAlert.setSubject(originalSendAlert.getSubject());
			sendAlert.setMessagetext(originalSendAlert.getMessagetext());
			sendAlert.setOwner(originalSendAlert.getOwner());
			sendAlert.setSendfromtype(originalSendAlert.getSendfromtype());
			sendAlert.setSendfrom(originalSendAlert.getSendfrom());
			sendAlert.setSendtotype(originalSendAlert.getSendtotype());
			sendAlert.setSendto(originalSendAlert.getSendto());
			sendAlert.setStatus(originalSendAlert.getStatus());
			sendAlert.setDateadded(originalSendAlert.getDateadded());
			sendAlert.setTimeadded(originalSendAlert.getTimeadded());
			sendAlert.setAction(originalSendAlert.getAction());
			sendAlert.setDatesent(originalSendAlert.getDatesent());
			sendAlert.setTimesent(originalSendAlert.getTimesent());
			sendAlert.setAttachment(originalSendAlert.getAttachment());
			sendAlert.setVendorId(originalSendAlert.getVendorId());
			sendAlert.setAttempts(originalSendAlert.getAttempts());

			incomingRequest.put("sendAlert", sendAlert);

			SendAlertAdd addTask = new SendAlertAdd();
			sendAlert = (SendAlert) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = sendAlert;
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
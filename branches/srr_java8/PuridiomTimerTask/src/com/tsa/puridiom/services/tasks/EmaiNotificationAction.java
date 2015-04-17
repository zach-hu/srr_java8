package com.tsa.puridiom.services.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class EmaiNotificationAction extends Task 
{

	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		
		try 
		{
			SendQueue sendQueue = (SendQueue)incomingRequest.get("sendQueue");
			
			
			this.setStatus(Status.SUCCEEDED);			
		} 
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw new TsaException("An error Ocurred sending email", e);
		}
		// TODO Auto-generated method stub
		return super.executeTask(object);
	}
	

}

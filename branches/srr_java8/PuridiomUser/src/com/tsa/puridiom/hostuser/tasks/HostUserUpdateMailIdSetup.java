package com.tsa.puridiom.hostuser.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class HostUserUpdateMailIdSetup extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
		    String	mailId = (String) incomingRequest.get("newHostUser_mailId");
		    
		    incomingRequest.put("HostUser_mailId", mailId);
		    
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
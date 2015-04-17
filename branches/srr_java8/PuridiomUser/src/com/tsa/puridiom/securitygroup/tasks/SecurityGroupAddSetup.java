package com.tsa.puridiom.securitygroup.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class SecurityGroupAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			if (!incomingRequest.containsKey("SecurityGroup_owner"))
			{
		    	incomingRequest.put("SecurityGroup_owner", incomingRequest.get("userId"));
			}
			if (!incomingRequest.containsKey("SecurityGroup_status"))
			{
			    incomingRequest.put("SecurityGroup_status", "02");
			}
			if (!incomingRequest.containsKey("SecurityGroup_dateEntered"))
			{
                String userTimeZone = (String) incomingRequest.get("userTimeZone");
			    incomingRequest.put("SecurityGroup_dateEntered", Dates.today("", userTimeZone));
			}

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
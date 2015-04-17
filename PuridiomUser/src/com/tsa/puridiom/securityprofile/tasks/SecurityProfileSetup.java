package com.tsa.puridiom.securityprofile.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class SecurityProfileSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("SecurityProfile_profileType", "");
			incomingRequest.put("SecurityProfile_groupId", "");
			incomingRequest.put("SecurityProfile_profile", "");
			incomingRequest.put("SecurityProfile_flags", "");
			incomingRequest.put("SecurityProfile_accessValue", "0");

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
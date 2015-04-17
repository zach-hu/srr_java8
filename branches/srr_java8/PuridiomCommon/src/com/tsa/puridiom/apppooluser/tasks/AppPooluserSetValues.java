package com.tsa.puridiom.apppooluser.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class AppPooluserSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			AppPooluserPK comp_id = new AppPooluserPK();
			AppPooluser appPooluser = (AppPooluser) incomingRequest.get("appPooluser");
			if (appPooluser == null)
			{
				appPooluser = new AppPooluser();
			}

			if (incomingRequest.containsKey("AppPooluser_poolid"))
			{
				String poolid = (String ) incomingRequest.get("AppPooluser_poolid");
				comp_id.setPoolid(poolid);
			}
			if (incomingRequest.containsKey("AppPooluser_userId"))
			{
				String userId = (String ) incomingRequest.get("AppPooluser_userId");
				comp_id.setUserId(userId);
			}
			appPooluser.setComp_id(comp_id);

			result = appPooluser;
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
package com.tsa.puridiom.apppooluser.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class AppPooluserSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain appPooluser */
			AppPooluserPK comp_id = new AppPooluserPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			AppPooluser	originalAppPooluser = (AppPooluser) incomingRequest.get("appPooluser");
			AppPooluser	appPooluser = new AppPooluser();

			comp_id.setPoolid(originalAppPooluser.getComp_id().getPoolid());
			comp_id.setUserId(originalAppPooluser.getComp_id().getUserId());
			appPooluser.setComp_id(comp_id);

			incomingRequest.put("appPooluser", appPooluser);

			AppPooluserAdd addTask = new AppPooluserAdd();
			appPooluser = (AppPooluser) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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
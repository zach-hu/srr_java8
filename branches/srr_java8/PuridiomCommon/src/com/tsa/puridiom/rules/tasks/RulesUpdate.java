package com.tsa.puridiom.rules.tasks;

import java.util.*;

import com.tsa.puridiom.entity.Rules;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RulesUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Rules rule = (Rules)incomingRequest.get("rules");
			if (rule == null)
			{
				throw new Exception ("rule was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			dbs.update(rule);

			result = rule;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}

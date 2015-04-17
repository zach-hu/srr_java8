package com.tsa.puridiom.apprulesext.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.AppRulesExt;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class AppRulesExtUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			AppRulesExt appRulesExt = (AppRulesExt)incomingRequest.get("appRulesExt");
			if (appRulesExt == null)
			{
				throw new Exception ("AppRulesExt was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(appRulesExt);
		
			result = appRulesExt;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}

package com.tsa.puridiom.apprulesext.tasks;

import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AppRulesExtRetrieveAll extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
	    Object result = null;
	
		Map incomingRequest = (Map)object;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String queryString = "from AppRulesExt";
			result = dbs.query(queryString) ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) 
		{
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
		return result ;
	}

}
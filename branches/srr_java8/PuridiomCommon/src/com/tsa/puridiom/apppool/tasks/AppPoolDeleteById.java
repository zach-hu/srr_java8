package com.tsa.puridiom.apppool.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class AppPoolDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		AppPool appPool = (AppPool)incomingRequest.get("appPool");
		if(appPool == null)
		{
			appPool = new AppPool();
		}
		AppPoolSetValuesPK setValues = new AppPoolSetValuesPK();
		setValues.setValues(incomingRequest, appPool);
		dbs.delete(appPool);
		this.setStatus(dbs.getStatus()) ;
		return appPool ;
	}

}
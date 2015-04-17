package com.tsa.puridiom.apppooluser.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.entity.*;

public class AppPooluserDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		AppPooluser appPooluser = (AppPooluser)incomingRequest.get("appPooluser");
		if(appPooluser == null)
		{
			appPooluser = new AppPooluser();
		}
		AppPooluserSetValuesPK setValues = new AppPooluserSetValuesPK();
		setValues.setValues(incomingRequest, appPooluser);
		dbs.delete(appPooluser);
		this.setStatus(dbs.getStatus()) ;
		return appPooluser ;
	}

}
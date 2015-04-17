package com.tsa.puridiom.posecurity.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoSecurity;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class PoSecurityDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		PoSecurity poSecurity = (PoSecurity)incomingRequest.get("poSecurity");
		if(poSecurity == null)
		{
			poSecurity = new PoSecurity();
		}
		PoSecuritySetValuesPK setValues = new PoSecuritySetValuesPK();
		setValues.setValues(incomingRequest, poSecurity);
		dbs.delete(poSecurity);
		this.setStatus(dbs.getStatus()) ;
		return poSecurity ;
	}

}
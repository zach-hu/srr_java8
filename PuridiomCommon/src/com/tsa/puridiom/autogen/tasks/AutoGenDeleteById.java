package com.tsa.puridiom.autogen.tasks;

import com.tsa.puridiom.entity.AutoGen;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class AutoGenDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		AutoGen autoGen = (AutoGen)incomingRequest.get("autoGen");
		if(autoGen == null)
		{
			autoGen = new AutoGen();
		}
		AutoGenSetValuesPK setValues = new AutoGenSetValuesPK();
		setValues.setValues(incomingRequest, autoGen);
		dbs.delete(autoGen);
		this.setStatus(dbs.getStatus()) ;
		return autoGen ;
	}

}
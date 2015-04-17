package com.tsa.puridiom.currcode.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.CurrCode;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class CurrCodeDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		CurrCode currCode = (CurrCode)incomingRequest.get("currCode");
		if(currCode == null)
		{
			currCode = new CurrCode();
		}
		CurrCodeSetValuesPK setValues = new CurrCodeSetValuesPK();
		setValues.setValues(incomingRequest, currCode);
		dbs.delete(currCode);
		this.setStatus(dbs.getStatus()) ;
		return currCode ;
	}

}
package com.tsa.puridiom.responsevalue.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ResponseValueDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		ResponseValue responseValue = (ResponseValue)incomingRequest.get("responseValue");
		if(responseValue == null)
		{
			responseValue = new ResponseValue();
		}
		ResponseValueSetValuesPK setValues = new ResponseValueSetValuesPK();
		setValues.setValues(incomingRequest, responseValue);
		dbs.delete(responseValue);
		this.setStatus(dbs.getStatus()) ;
		return responseValue ;
	}

}
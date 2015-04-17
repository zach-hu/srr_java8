package com.tsa.puridiom.invformdata.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.InvFormData;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class InvFormDataDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InvFormData invFormData = (InvFormData)incomingRequest.get("invFormData");
		if(invFormData == null)
		{
			invFormData = new InvFormData();
		}
		InvFormDataSetValuesPK setValues = new InvFormDataSetValuesPK();
		setValues.setValues(incomingRequest, invFormData);
		dbs.delete(invFormData);
		this.setStatus(dbs.getStatus()) ;
		return invFormData ;
	}

}
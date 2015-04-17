package com.tsa.puridiom.labels.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import java.util.Map;

public class LabelsAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		Labels label = (Labels)incomingRequest.get("labels");
		try
		{

			if (label == null)
			{
				throw new Exception ("Label was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			    dbs.add(label);
			result = label;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.error(this, label.toString());
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
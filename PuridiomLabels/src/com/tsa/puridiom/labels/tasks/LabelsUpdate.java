package com.tsa.puridiom.labels.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.Labels;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class LabelsUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Labels labels = (Labels)incomingRequest.get("labels");
			if (labels == null)
			{
				throw new Exception ("Labels was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(labels);

			result = labels;
			this.setStatus(dbs.getStatus()) ;
			/* Had to flush here, so data was available for selection later */
			dbs.getSession().flush() ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}

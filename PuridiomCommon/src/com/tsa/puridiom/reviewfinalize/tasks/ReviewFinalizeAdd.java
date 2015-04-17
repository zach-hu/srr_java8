package com.tsa.puridiom.reviewfinalize.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReviewFinalize;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;


public class ReviewFinalizeAdd extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReviewFinalize reviewFinalize = (ReviewFinalize)incomingRequest.get("reviewfinalize");
			if (reviewFinalize == null)
			{
				throw new Exception ("ReviewFinalize was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(reviewFinalize);

			result = reviewFinalize;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
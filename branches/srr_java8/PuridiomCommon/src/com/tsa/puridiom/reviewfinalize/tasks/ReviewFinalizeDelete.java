package com.tsa.puridiom.reviewfinalize.tasks;

import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class ReviewFinalizeDelete extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		ReviewFinalize reviewFinalize = (ReviewFinalize)incomingRequest.get("reviewfinalize");
		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");

			if (reviewFinalize == null){		return null;		}

			dbs.delete(reviewFinalize);
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}
		return reviewFinalize;
	}

}
package com.tsa.puridiom.reviewfinalize.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class PoReviewFinalizeUpdate extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			ReviewFinalize reviewfinalize = (ReviewFinalize) incomingRequest.get("reviewfinalizebyid");
			if (reviewfinalize == null)
			{
				throw new Exception("ReviewFinalize was not found.");
			}

			DBSession dbs = (DBSession) incomingRequest.get("dbsession");

			dbs.update(reviewfinalize);

			result = reviewfinalize;
			this.setStatus(dbs.getStatus());

			/* Had to flush here, so data was available for selection later */
			dbs.getSession().flush(); // esto esta diferente en address
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
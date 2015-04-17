package com.tsa.puridiom.reviewfinalize.tasks;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class PoReviewFinalizeRetrieveById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String icString = (String) incomingRequest.get("PoHeader_icPoHeader");
			if (Utility.isEmpty(icString))
			{
				throw new Exception("ReviewFinalize_icHeader cannot be empty.  ReviewFinalize could not be retrieved.");
			}
			BigDecimal ic = new BigDecimal(icString);

			String queryString = "from ReviewFinalize as ReviewFinalize where ReviewFinalize.icHeader = ? ";
			result = dbs.query(queryString, new Object[] { ic } , new Type[] { Hibernate.BIG_DECIMAL });
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
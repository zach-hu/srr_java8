package com.tsa.puridiom.vendor.performance.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class PerformanceDetailAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PerformanceDetail performanceDetail = (PerformanceDetail)incomingRequest.get("performanceDetail");
			if (performanceDetail == null)
			{
				throw new Exception ("Performance Detail Record was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(performanceDetail);

			result = performanceDetail;
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
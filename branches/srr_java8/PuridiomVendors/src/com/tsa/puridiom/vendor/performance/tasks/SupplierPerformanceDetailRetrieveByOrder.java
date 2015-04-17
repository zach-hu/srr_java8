package com.tsa.puridiom.vendor.performance.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.Map;

public class SupplierPerformanceDetailRetrieveByOrder extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("PerformanceDetail_icPoHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );

			String queryString = "from PerformanceDetail as PerformanceDetail where PerformanceDetail.id.icPoHeader = ?";
			result = dbs.query(queryString, new Object[] {icHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;
			Log.debug(this, "performance: " + result);
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
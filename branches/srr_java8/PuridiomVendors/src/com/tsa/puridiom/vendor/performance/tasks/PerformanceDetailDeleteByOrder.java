/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.vendor.performance.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;

import java.util.*;
import java.math.*;
import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Administrator
 */
public class PerformanceDetailDeleteByOrder extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;

		try
		{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession");

	        String icHeader = (String)incomingRequest.get("PerformanceDetail_icPoHeader");
			BigDecimal bdHeader = new BigDecimal(icHeader);

	        String queryString = "from PerformanceDetail as p where p.id.icPoHeader = ?";

	        this.setStatus(dbs.delete(queryString, new Object[] {bdHeader}, new Type[] {Hibernate.BIG_DECIMAL})) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Performance detail Record could not be deleted. " + e.getMessage(), e);
		}

		return ret ;
	}
}

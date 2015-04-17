package com.tsa.puridiom.disbheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class DisbHeaderRetrieveByIdHistory extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icDisbHeaderString = (String) incomingRequest.get("old_DisbHeader_icDsbHeader");
			if (Utility.isEmpty(icDisbHeaderString))
			{
				throw new Exception("DisbHeader_icDsbHeader cannot be empty.  Disbursement header could not be retrieved.");
			}
			BigDecimal icDisbHeader = new BigDecimal ( icDisbHeaderString );

			String queryString = "from DisbHeader as header where header.icDsbHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icDisbHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
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
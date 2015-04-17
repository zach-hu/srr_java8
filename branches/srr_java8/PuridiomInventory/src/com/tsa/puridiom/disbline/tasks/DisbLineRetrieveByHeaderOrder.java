package com.tsa.puridiom.disbline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class DisbLineRetrieveByHeaderOrder extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icDsbHeaderString = (String) incomingRequest.get("DisbLine_icDsbHeader");
			BigDecimal icDsbHeader = new BigDecimal ( icDsbHeaderString );

			String queryString = "from DisbLine as disbLine where disbLine.icDsbHeader = ? Order by disbLine.icDsbHeader";
			result = dbs.query(queryString, new Object[] {icDsbHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
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
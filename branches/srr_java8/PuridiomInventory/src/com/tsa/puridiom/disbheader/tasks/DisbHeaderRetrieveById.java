package com.tsa.puridiom.disbheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DisbHeaderRetrieveById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icDsbHeaderString = (String) incomingRequest.get("DisbHeader_icDsbHeader");
			BigDecimal icDsbHeader = new BigDecimal ( icDsbHeaderString );

			String queryString = "from DisbHeader as DisbHeader where DisbHeader.id = ? ";
			List resultList = dbs.query(queryString, new Object[] {icDsbHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		return result;
	}

}
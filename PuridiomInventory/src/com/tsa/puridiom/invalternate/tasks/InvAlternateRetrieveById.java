package com.tsa.puridiom.invalternate.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InvAlternateRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icAlternateString = (String) incomingRequest.get("InvAlternate_icAlternate");
			BigDecimal icAlternate = new BigDecimal ( icAlternateString );

			String queryString = "from InvAlternate as InvAlternate where InvAlternate.id.icAlternate = ? ";
			List resultList = dbs.query(queryString, new Object[] {icAlternate, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
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
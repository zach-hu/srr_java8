package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class PoLineRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			//String icPoHeaderString = (String) incomingRequest.get("PoLine_icPoHeader");
			//BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
			String icPoLineString = (String) incomingRequest.get("PoLine_icPoLine");
			if (Utility.isEmpty(icPoLineString))
			{
				throw new Exception("PoLine_icPoLine cannot be empty.  PO line could not be retrieved.");
			}
			BigDecimal icPoLine = new BigDecimal ( icPoLineString );

			//String queryString = "from PoLine as PoLine where PoLine.id.icPoHeader = ? and PoLine.id.icPoLine = ? ";
			//List resultList = dbs.query(queryString, new Object[] {icPoHeader, icPoLine, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
			
			String queryString = "from PoLine as PoLine where PoLine.icPoLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icPoLine, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
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
package com.tsa.puridiom.disbheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DisbHeaderGetSubtotal extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icDsbHeaderString = (String) incomingRequest.get("DisbHeader_icDsbHeader");
			BigDecimal icDsbHeader = new BigDecimal ( icDsbHeaderString );
			BigDecimal dsbHeaderSubtotal = new BigDecimal("0");

			String queryString = "select d.subtotal from DisbHeader as d where d.icDsbHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icDsbHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				dsbHeaderSubtotal = (BigDecimal) resultList.get(0);
				if (dsbHeaderSubtotal == null) {
					dsbHeaderSubtotal = new BigDecimal(0) ;
				}
			}
			result = String.valueOf(dsbHeaderSubtotal);
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
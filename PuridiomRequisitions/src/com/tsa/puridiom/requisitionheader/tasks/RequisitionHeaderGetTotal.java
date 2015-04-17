package com.tsa.puridiom.requisitionheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RequisitionHeaderGetTotal extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icReqHeaderString = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
			BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
			BigDecimal reqHeaderTotal = new BigDecimal("0");

			String queryString = "select r.total from RequisitionHeader as r where r.icReqHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icReqHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				reqHeaderTotal = (BigDecimal) resultList.get(0);
				if (reqHeaderTotal == null) {
					reqHeaderTotal = new BigDecimal(0) ;
				}
			}
			result = String.valueOf(reqHeaderTotal);
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
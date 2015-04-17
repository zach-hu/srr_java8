package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionLineQuantityRequested extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icReqLineString = (String) incomingRequest.get("RequisitionLine_icReqLine");
			BigDecimal icReqLine = new BigDecimal ( icReqLineString );
			BigDecimal quantity = new BigDecimal("0");

			String queryString = "select r.quantity from RequisitionLine as r where r.icReqLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icReqLine, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
			    quantity = (BigDecimal) resultList.get(0);
				if (quantity == null) {
				    quantity = new BigDecimal(0) ;
				}
			}
			result = quantity;
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
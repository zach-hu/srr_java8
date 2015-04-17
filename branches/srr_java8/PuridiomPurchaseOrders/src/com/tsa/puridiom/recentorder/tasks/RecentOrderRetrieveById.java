package com.tsa.puridiom.recentorder.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RecentOrderRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String buyerCode = (String ) incomingRequest.get("RecentOrder_buyerCode");
			String icPoHeaderString = (String ) incomingRequest.get("RecentOrder_icPoHeader");

			BigDecimal icPoHeader = new BigDecimal(icPoHeaderString);
			
			String queryString = "from RecentOrder as RecentOrder where RecentOrder.id.buyerCode = ? and RecentOrder.id.icPoHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {buyerCode, icPoHeader, } , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;
					
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
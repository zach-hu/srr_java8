package com.tsa.puridiom.recentorderitem.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RecentOrderItemRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String buyerCode = (String ) incomingRequest.get("RecentOrderItem_buyerCode");
			String itemNumber = (String ) incomingRequest.get("RecentOrderItem_itemNumber");
			String itemLocation = (String ) incomingRequest.get("RecentOrderItem_itemLocation");

			String queryString = "from RecentOrderItem as RecentOrderItem where RecentOrderItem.id.buyerCode = ? and RecentOrderItem.id.itemNumber = ? and RecentOrderItem.id.itemLocation = ? ";
			List resultList = dbs.query(queryString, new Object[] {buyerCode, itemNumber, itemLocation, } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;
					
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
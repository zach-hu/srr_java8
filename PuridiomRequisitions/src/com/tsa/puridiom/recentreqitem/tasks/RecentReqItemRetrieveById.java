package com.tsa.puridiom.recentreqitem.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RecentReqItemRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String requisitionerCode = (String ) incomingRequest.get("RecentReqItem_requisitionerCode");
			String itemNumber = (String ) incomingRequest.get("RecentReqItem_itemNumber");
			String itemLocation = (String ) incomingRequest.get("RecentReqItem_itemLocation");

			String queryString = "from RecentReqItem as RecentReqItem where RecentReqItem.id.requisitionerCode = ? and RecentReqItem.id.itemNumber = ? and RecentReqItem.id.itemLocation = ? ";
			List resultList = dbs.query(queryString, new Object[] {requisitionerCode, itemNumber, itemLocation, } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;
					
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
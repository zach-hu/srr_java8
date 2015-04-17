package com.tsa.puridiom.recentorder.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class RecentOrderRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from RecentOrder as recentorder where 1=1 ");
		if(incomingRequest.containsKey("RecentOrder_buyerCode"))
		{			
			String buyerCode = (String) incomingRequest.get("RecentOrder_buyerCode");
			queryString.append(" AND recentorder.id.buyerCode = '" + buyerCode + "'");
		}
		if(incomingRequest.containsKey("RecentOrder_icPoHeader"))
		{			
			String icPoHeader = (String) incomingRequest.get("RecentOrder_icPoHeader");
			queryString.append(" AND recentorder.id.icPoHeader = '" + icPoHeader + "'");
		}
		if(incomingRequest.containsKey("RecentOrder_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("RecentOrder_dateEntered");
			queryString.append(" AND recentorder.dateEntered = '" + dateEntered + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
package com.tsa.puridiom.recentorderitem.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class RecentOrderItemRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from RecentOrderItem as recentorderitem where 1=1 ");
		if(incomingRequest.containsKey("RecentOrderItem_buyerCode"))
		{
			String buyerCode = (String) incomingRequest.get("RecentOrderItem_buyerCode");
			queryString.append(" AND recentorderitem.id.buyerCode = '" + buyerCode + "'");
		}
		if(incomingRequest.containsKey("RecentOrderItem_itemNumber"))
		{
			String itemNumber = (String) incomingRequest.get("RecentOrderItem_itemNumber");
			queryString.append(" AND recentorderitem.id.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("RecentOrderItem_itemLocation"))
		{
			String itemLocation = (String) incomingRequest.get("RecentOrderItem_itemLocation");
			queryString.append(" AND recentorderitem.id.itemLocation = '" + itemLocation + "'");
		}
		if(incomingRequest.containsKey("RecentOrderItem_itemSource"))
		{
			String itemSource = (String) incomingRequest.get("RecentOrderItem_itemSource");
			queryString.append(" AND recentorderitem.itemSource = '" + itemSource + "'");
		}
		if(incomingRequest.containsKey("RecentOrderItem_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("RecentOrderItem_dateEntered");
			queryString.append(" AND recentorderitem.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("RecentOrderItem_orderCount"))
		{
			String orderCount = (String) incomingRequest.get("RecentOrderItem_orderCount");
			queryString.append(" AND recentorderitem.orderCount = '" + orderCount + "'");
		}

		queryString.append(" ORDER BY recentorderitem.orderCount DESC, recentorderitem.dateEntered DESC");

		List result = dbs.query(queryString.toString(), new Object[] {}, 11) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
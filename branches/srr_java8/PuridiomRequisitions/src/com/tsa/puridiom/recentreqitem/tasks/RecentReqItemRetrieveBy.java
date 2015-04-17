package com.tsa.puridiom.recentreqitem.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class RecentReqItemRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from RecentReqItem as recentreqitem where 1=1 ");
		if(incomingRequest.containsKey("RecentReqItem_requisitionerCode"))
		{
			String requisitionerCode = (String) incomingRequest.get("RecentReqItem_requisitionerCode");
			queryString.append(" AND recentreqitem.id.requisitionerCode = '" + requisitionerCode + "'");
		}
		if(incomingRequest.containsKey("RecentReqItem_itemNumber"))
		{
			String itemNumber = (String) incomingRequest.get("RecentReqItem_itemNumber");
			queryString.append(" AND recentreqitem.id.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("RecentReqItem_itemLocation"))
		{
			String itemLocation = (String) incomingRequest.get("RecentReqItem_itemLocation");
			queryString.append(" AND recentreqitem.id.itemLocation = '" + itemLocation + "'");
		}
		if(incomingRequest.containsKey("RecentReqItem_itemSource"))
		{
			String itemSource = (String) incomingRequest.get("RecentReqItem_itemSource");
			queryString.append(" AND recentreqitem.itemSource = '" + itemSource + "'");
		}
		if(incomingRequest.containsKey("RecentReqItem_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("RecentReqItem_dateEntered");
			queryString.append(" AND recentreqitem.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("RecentReqItem_requestCount"))
		{
			String requestCount = (String) incomingRequest.get("RecentReqItem_requestCount");
			queryString.append(" AND recentreqitem.requestCount = '" + requestCount + "'");
		}

		queryString.append(" ORDER BY recentreqitem.requestCount DESC, recentreqitem.dateEntered DESC");

		List result = dbs.query(queryString.toString(), new Object[] {}, 11) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
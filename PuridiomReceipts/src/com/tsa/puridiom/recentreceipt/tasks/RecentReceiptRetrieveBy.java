package com.tsa.puridiom.recentreceipt.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class RecentReceiptRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from RecentReceipt as recentreceipt where 1=1 ");
		if(incomingRequest.containsKey("RecentReceipt_receivedBy"))
		{			
			String receivedBy = (String) incomingRequest.get("RecentReceipt_receivedBy");
			queryString.append(" AND recentreceipt.id.receivedBy = '" + receivedBy + "'");
		}
		if(incomingRequest.containsKey("RecentReceipt_icRecHeader"))
		{			
			String icRecHeader = (String) incomingRequest.get("RecentReceipt_icRecHeader");
			queryString.append(" AND recentreceipt.id.icRecHeader = '" + icRecHeader + "'");
		}
		if(incomingRequest.containsKey("RecentReceipt_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("RecentReceipt_dateEntered");
			queryString.append(" AND recentreceipt.dateEntered = '" + dateEntered + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
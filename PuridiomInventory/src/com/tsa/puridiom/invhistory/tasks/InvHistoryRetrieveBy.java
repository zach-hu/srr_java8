package com.tsa.puridiom.invhistory.tasks;
import java.util.List;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class InvHistoryRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvHistory as invhistory where 1=1 ");
		if(incomingRequest.containsKey("InvHistory.seqNumber"))
		{			
			String seqNumber = (String) incomingRequest.get("InvHistory.seqNumber");
			queryString.append(" AND invhistory.id.seqNumber = '" + seqNumber + "'");
		}
		if(incomingRequest.containsKey("InvHistory.itemNumber"))
		{			
			String itemNumber = (String) incomingRequest.get("InvHistory.itemNumber");
			queryString.append(" AND invhistory.id.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvHistory.docPrtDate"))
		{			
			String docPrtDate = (String) incomingRequest.get("InvHistory.docPrtDate");
			queryString.append(" AND invhistory.id.docPrtDate = '" + docPrtDate + "'");
		}
		if(incomingRequest.containsKey("InvHistory.primUser"))
		{			
			String primUser = (String) incomingRequest.get("InvHistory.primUser");
			queryString.append(" AND invhistory.id.primUser = '" + primUser + "'");
		}
		if(incomingRequest.containsKey("InvHistory.puAppDate"))
		{			
			String puAppDate = (String) incomingRequest.get("InvHistory.puAppDate");
			queryString.append(" AND invhistory.id.puAppDate = '" + puAppDate + "'");
		}
		if(incomingRequest.containsKey("InvHistory.faId"))
		{			
			String faId = (String) incomingRequest.get("InvHistory.faId");
			queryString.append(" AND invhistory.id.faId = '" + faId + "'");
		}
		if(incomingRequest.containsKey("InvHistory.faAppDate"))
		{			
			String faAppDate = (String) incomingRequest.get("InvHistory.faAppDate");
			queryString.append(" AND invhistory.id.faAppDate = '" + faAppDate + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
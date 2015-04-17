package com.tsa.puridiom.recentrfq.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecentRfqRetrieveHeaderBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from RecentRfq as recentrfq, RfqHeader rfqheader where 1=1 ");
		if(incomingRequest.containsKey("RecentRfq_buyerCode"))
		{			
			String buyerCode = (String) incomingRequest.get("RecentRfq_buyerCode");
			queryString.append(" AND rfqheader.buyer = '" + buyerCode + "'");
		}
		if(incomingRequest.containsKey("RecentRfq_icRfqHeader"))
		{			
			String icRfqHeader = (String) incomingRequest.get("RecentRfq_icRfqHeader");
			queryString.append(" AND recentrfq.id.icRfqHeader = '" + icRfqHeader + "'");
		}
		if(incomingRequest.containsKey("RecentRfq_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("RecentRfq_dateEntered");
			queryString.append(" AND recentrfq.dateEntered = '" + dateEntered + "'");
		}
		
		queryString.append(" AND recentrfq.id.icRfqHeader = rfqheader.icRfqHeader");
		queryString.append(" ORDER BY recentrfq.id.icRfqHeader DESC");
		
		List list = dbs.query(queryString.toString(), new Object[] {}, 11) ;
		List	result = new ArrayList();
		
		if (list != null && list.size() > 0) {
		    for (int i=0; i < list.size(); i++ ) {
		        Object obj[] = (Object[])list.get(i);
		        RfqHeader rfqHeader = (RfqHeader) obj[1];
		        result.add(rfqHeader);
		    }
		}
		
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
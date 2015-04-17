package com.tsa.puridiom.costrange.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class CostRangeRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from CostRange as costrange where 1=1 ");
		if(incomingRequest.containsKey("CostRange_icCostRange"))
		{			
			String icCostRange = (String) incomingRequest.get("CostRange_icCostRange");
			queryString.append(" AND costrange.id.icCostRange = '" + icCostRange + "'");
		}
		if(incomingRequest.containsKey("CostRange_itemType"))
		{			
			String itemType = (String) incomingRequest.get("CostRange_itemType");
			queryString.append(" AND costrange.itemType = '" + itemType + "'");
		}
		if(incomingRequest.containsKey("CostRange_description"))
		{			
			String description = (String) incomingRequest.get("CostRange_description");
			queryString.append(" AND costrange.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("CostRange_minimumCost"))
		{			
			String minimumCost = (String) incomingRequest.get("CostRange_minimumCost");
			queryString.append(" AND costrange.minimumCost = '" + minimumCost + "'");
		}
		if(incomingRequest.containsKey("CostRange_maximumCost"))
		{			
			String maximumCost = (String) incomingRequest.get("CostRange_maximumCost");
			queryString.append(" AND costrange.maximumCost = '" + maximumCost + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
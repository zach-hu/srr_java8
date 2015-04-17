package com.tsa.puridiom.userstatistic.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class UserStatisticRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from UserStatistic as userstatistic where 1=1 ");
		if(incomingRequest.containsKey("UserStatistic_userId"))
		{			
			String userId = (String) incomingRequest.get("UserStatistic_userId");
			queryString.append(" AND userstatistic.id.userId = '" + userId + "'");
		}
		if(incomingRequest.containsKey("UserStatistic_statType"))
		{			
			String statType = (String) incomingRequest.get("UserStatistic_statType");
			queryString.append(" AND userstatistic.id.statType = '" + statType + "'");
		}
		if(incomingRequest.containsKey("UserStatistic_statKey"))
		{			
			String statKey = (String) incomingRequest.get("UserStatistic_statKey");
			queryString.append(" AND userstatistic.id.statKey = '" + statKey + "'");
		}
		if(incomingRequest.containsKey("UserStatistic_statYear"))
		{			
			String statYear = (String) incomingRequest.get("UserStatistic_statYear");
			queryString.append(" AND userstatistic.id.statYear = '" + statYear + "'");
		}
		if(incomingRequest.containsKey("UserStatistic_statMonth"))
		{			
			String statMonth = (String) incomingRequest.get("UserStatistic_statMonth");
			queryString.append(" AND userstatistic.id.statMonth = '" + statMonth + "'");
		}
		if(incomingRequest.containsKey("UserStatistic_statCount"))
		{			
			String statCount = (String) incomingRequest.get("UserStatistic_statCount");
			queryString.append(" AND userstatistic.statCount = '" + statCount + "'");
		}
		if(incomingRequest.containsKey("UserStatistic_statTotal"))
		{			
			String statTotal = (String) incomingRequest.get("UserStatistic_statTotal");
			queryString.append(" AND userstatistic.statTotal = '" + statTotal + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
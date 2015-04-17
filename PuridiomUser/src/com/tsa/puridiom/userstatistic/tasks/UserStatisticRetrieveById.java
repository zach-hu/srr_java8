package com.tsa.puridiom.userstatistic.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class UserStatisticRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String userId = (String ) incomingRequest.get("UserStatistic_userId");
			String statType = (String ) incomingRequest.get("UserStatistic_statType");
			String statKey = (String ) incomingRequest.get("UserStatistic_statKey");
			String statYear = (String ) incomingRequest.get("UserStatistic_statYear");
			String statMonth = (String ) incomingRequest.get("UserStatistic_statMonth");

			String queryString = "from UserStatistic as UserStatistic where UserStatistic.id.userId = ? and UserStatistic.id.statType = ? and UserStatistic.id.statKey = ? and UserStatistic.id.statYear = ? and UserStatistic.id.statMonth = ? ";
			List resultList = dbs.query(queryString, new Object[] {userId, statType, statKey, statYear, statMonth, } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;
					
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
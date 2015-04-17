package com.tsa.puridiom.userstatistic.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class UserStatisticDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		UserStatistic userStatistic = (UserStatistic)incomingRequest.get("userStatistic");
		if(userStatistic == null)
		{
			userStatistic = new UserStatistic();
		}
		UserStatisticSetValuesPK setValues = new UserStatisticSetValuesPK();
		setValues.setValues(incomingRequest, userStatistic);
		dbs.delete(userStatistic);
		this.setStatus(dbs.getStatus()) ;
		return userStatistic ;
	}

}
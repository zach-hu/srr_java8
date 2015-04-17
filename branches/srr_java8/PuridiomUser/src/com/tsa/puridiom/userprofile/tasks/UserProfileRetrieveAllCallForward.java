package com.tsa.puridiom.userprofile.tasks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class UserProfileRetrieveAllCallForward extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Date today = new Date();
		String queryString = "from UserProfile as userProfile where userProfile.forwardOffDate < ? AND " +
							 "userProfile.callForward is not null ";
		List resultList = dbs.query(queryString, new Object[] {today } , new Type[] { Hibernate.DATE}) ;

		this.setStatus(dbs.getStatus()) ;
		return resultList ;
	}

}
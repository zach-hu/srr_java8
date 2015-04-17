package com.tsa.puridiom.usergrouprel.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class UserGroupRelRetrieveByUserId extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String userId = (String ) incomingRequest.get("UserGroupRel_userId");

		String queryString = "from UserGroupRel as UserGroupRel where UserGroupRel.id.userId = ? ";
		List resultList = dbs.query(queryString, new Object[] { userId } , new Type[] { Hibernate.STRING}) ;
		
		this.setStatus(dbs.getStatus()) ;
		
		return resultList;
	}
}
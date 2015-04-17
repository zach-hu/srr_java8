package com.tsa.puridiom.usergrouprel.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;
import org.hibernate.*;

public class UserGroupRelDeleteByUserId extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Object	userIdObj = incomingRequest.get("UserGroupRel_userId");
		String	userId = null;
		
		if (userIdObj instanceof String[])
		{
			userId = ((String[]) userIdObj)[0];
		}
		else
		{
			userId = (String) userIdObj;
		}
		
		String queryString = "from UserGroupRel as UserGroupRel where UserGroupRel.id.userId = ?" ;

		int retval = dbs.delete(queryString, userId, Hibernate.STRING) ;
		
		this.setStatus(dbs.getStatus()) ;
		return null ;
	}

}
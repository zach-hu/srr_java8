package com.tsa.puridiom.usergrouprel.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class UserGroupRelRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from UserGroupRel as usergrouprel where 1=1 ");
		if(incomingRequest.containsKey("UserGroupRel_groupId"))
		{			
			String groupId = (String) incomingRequest.get("UserGroupRel_groupId");
			queryString.append(" AND usergrouprel.id.groupId = '" + groupId + "'");
		}
		if(incomingRequest.containsKey("UserGroupRel_userId"))
		{			
			String userId = (String) incomingRequest.get("UserGroupRel_userId");
			queryString.append(" AND usergrouprel.id.userId = '" + userId + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
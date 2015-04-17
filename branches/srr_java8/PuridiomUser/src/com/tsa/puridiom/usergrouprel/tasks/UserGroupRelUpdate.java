package com.tsa.puridiom.usergrouprel.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class UserGroupRelUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			UserGroupRel userGroupRel = (UserGroupRel)incomingRequest.get("userGroupRel");
			if (userGroupRel == null)
			{
				throw new Exception ("UserGroupRel was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(userGroupRel);
		
			result = userGroupRel;
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
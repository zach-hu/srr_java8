package com.tsa.puridiom.securityprofile.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;
import org.hibernate.*;

public class SecurityProfileDeleteByGroupId extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Object		groupIdObj = incomingRequest.get("SecurityProfile_groupId");
		String	groupId = null;
		
		if (groupIdObj instanceof String[])
		{
		    groupId = ((String[]) groupIdObj)[0];
		}
		else
		{
		    groupId = (String) groupIdObj;
		}
		
		String queryString = "from SecurityProfile as SecurityProfile where SecurityProfile.id.groupId = ?" ;

		int retval = dbs.delete(queryString, groupId, Hibernate.STRING) ;
		
		this.setStatus(dbs.getStatus()) ;
		return null ;
	}

}
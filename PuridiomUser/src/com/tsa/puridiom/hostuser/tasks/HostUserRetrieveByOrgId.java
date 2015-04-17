package com.tsa.puridiom.hostuser.tasks;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class HostUserRetrieveByOrgId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    // Always use host database configuration for this table
		    DBSession dbs = new DBSession("host");
			String orgId = (String ) incomingRequest.get("HostUser_organizationId");
			String userId = (String ) incomingRequest.get("HostUser_userId");
			if (userId == null) userId = "" ;
			if (userId.length() > 15)	userId = userId.substring(0, 15) ;

			String queryString = "from HostUser as HostUser where HostUser.organizationId = ? and HostUser.userId = ?";
			List resultList = dbs.query(queryString, new Object[] {orgId, userId } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;

			dbs.close() ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
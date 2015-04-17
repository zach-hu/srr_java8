package com.tsa.puridiom.securityprofile.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class SecurityProfileRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from SecurityProfile as securityprofile where 1=1 ");
		if(incomingRequest.containsKey("SecurityProfile_profileType"))
		{			
			String profileType = (String) incomingRequest.get("SecurityProfile_profileType");
			queryString.append(" AND securityprofile.id.profileType = '" + profileType + "'");
		}
		if(incomingRequest.containsKey("SecurityProfile_groupId"))
		{			
			String groupId = (String) incomingRequest.get("SecurityProfile_groupId");
			queryString.append(" AND securityprofile.id.groupId = '" + groupId + "'");
		}
		if(incomingRequest.containsKey("SecurityProfile_profile"))
		{			
			String profile = (String) incomingRequest.get("SecurityProfile_profile");
			queryString.append(" AND securityprofile.id.profile = '" + profile + "'");
		}
		if(incomingRequest.containsKey("SecurityProfile_flags"))
		{			
			String flags = (String) incomingRequest.get("SecurityProfile_flags");
			queryString.append(" AND securityprofile.flags = '" + flags + "'");
		}
		if(incomingRequest.containsKey("SecurityProfile_accessValue"))
		{			
			String accessValue = (String) incomingRequest.get("SecurityProfile_accessValue");
			queryString.append(" AND securityprofile.accessValue = '" + accessValue + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
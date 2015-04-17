package com.tsa.puridiom.hostuser.tasks;

import com.tsa.puridiom.entity.HostUser;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class HostUserSetUserProfileInfo extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
		    HostUser hostUser = (HostUser) incomingRequest.get("hostUser");
		    
		    if (hostUser != null) {
			    incomingRequest.put("UserProfile_mailId", hostUser.getMailId());
			    incomingRequest.put("UserProfile_organizationId", hostUser.getOrganizationId());
			    incomingRequest.put("UserProfile_userId", hostUser.getUserId());
			    incomingRequest.put("UserGroupRel_userId", hostUser.getUserId());
			    
			    incomingRequest.put("organizationId", hostUser.getOrganizationId());
		    }
		    
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
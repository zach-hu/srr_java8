package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class UserProfileDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		UserProfile userProfile = (UserProfile)incomingRequest.get("userProfile");
		if(userProfile == null)
		{
			userProfile = new UserProfile();
			String	userId = (String) incomingRequest.get("UserProfile_userId");
			userProfile.setUserId(userId);
		}

		dbs.delete(userProfile);
		this.setStatus(dbs.getStatus()) ;
		return userProfile ;
	}

}
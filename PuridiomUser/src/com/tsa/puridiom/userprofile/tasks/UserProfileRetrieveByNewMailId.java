package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.HashMap;
import java.util.Map;

public class UserProfileRetrieveByNewMailId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			Map requestParameters = new HashMap();
			
			requestParameters.put("dbsession", incomingRequest.get("dbsession"));
			requestParameters.put("organizationId", incomingRequest.get("organizationId"));
			requestParameters.put("userId", incomingRequest.get("userId"));
			requestParameters.put("UserProfile_mailId", incomingRequest.get("newUserProfile_mailId"));
			
			
			if(incomingRequest.get("newUserProfile_mailId")!=null)
			{
				UserProfileRetrieveByMailId retrieveTask = new UserProfileRetrieveByMailId();
				UserProfile userProfile = (UserProfile) retrieveTask.executeTask(requestParameters);
				result = userProfile;
			}
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
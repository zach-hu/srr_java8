package com.tsa.puridiom.authentication.tasks;

import com.tsa.puridiom.token.TokenManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class AuthenticatedSessionSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		String organizationId = (String) incomingRequest.get("organizationId") ;
		String userId = (String) incomingRequest.get("UserProfile_userId") ;
		String sessionId = (String) incomingRequest.get("sessionId") ;
		

		try {
			incomingRequest.put("userTokenId", TokenManager.getInstance().createToken(sessionId, organizationId, userId)) ;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
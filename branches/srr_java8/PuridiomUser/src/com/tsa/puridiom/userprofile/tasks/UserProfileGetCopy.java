package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class UserProfileGetCopy extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			UserProfile userProfile = (UserProfile) incomingRequest.get("userProfileCopy");

			result = userProfile;
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
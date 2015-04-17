package com.tsa.puridiom.userprofile.tasks;

import java.util.Date;
import java.util.Map;

import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

public class UserProfileCreateSetup extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
        Object result = null;
		try
        {
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
			String callForward = propertiesManager.getProperty("USER DEFAULTS", "CALLFORWARD", "");
			incomingRequest.put("UserProfile_callForward", callForward);
			this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw e;
        }
		return result;
	}

}

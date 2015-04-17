package com.tsa.puridiom.authentication.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.common.utility.BlackBoxTempCheck;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class AuthenticationSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String	organizationId = (String) incomingRequest.get("organizationId");
		    String	password = (String) incomingRequest.get("password");
			boolean caseSensitive = PropertiesManager.getInstance(organizationId).getProperty("MISC", "PassCaseSensitive", "N" ).equals("Y");
			
			if (!Utility.isEmpty(password)) {
				incomingRequest.put("p", password);
				
				BlackBoxTempCheck x = new BlackBoxTempCheck();
				Boolean temp = (Boolean) x.executeTask(incomingRequest);
				
				if (temp.booleanValue()) {
				    UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");
				    if (userProfile != null) {
				        userProfile.setTempPassword(true);
				    }
				}
				
			    if (!caseSensitive) {
			        password = password.toUpperCase();
			    }
				password = BlackBox.getEncrypt(password);
				
				incomingRequest.put("password", password);
			}

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
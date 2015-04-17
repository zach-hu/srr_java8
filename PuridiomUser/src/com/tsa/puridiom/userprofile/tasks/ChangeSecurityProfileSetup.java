package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.exceptions.PasswordSecurityException;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class ChangeSecurityProfileSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			String	userId = (String) incomingRequest.get("userId");
			String	securityAnswer = (String) incomingRequest.get("securityAnswer");
			String	newSecurityQuestion = (String) incomingRequest.get("newSecurityQuestion");
			String	newSecurityAnswer = (String) incomingRequest.get("newSecurityAnswer");

			if (incomingRequest.containsKey("securityAnswer") && Utility.isEmpty(securityAnswer)) {
				throw new PasswordSecurityException("You must answer your current security question!");
			}
			if (Utility.isEmpty(newSecurityQuestion)) {
				throw new PasswordSecurityException("You must select a new security question!");
			}
			if (Utility.isEmpty(newSecurityAnswer)) {
				throw new PasswordSecurityException("You must answer your new security question!");
			}

			if (Utility.isEmpty(securityAnswer)) {
			    securityAnswer = "";
			} else {
			    securityAnswer = securityAnswer.trim();   
			}
			newSecurityAnswer = newSecurityAnswer.trim();
			
			incomingRequest.put("UserProfile_organizationId", organizationId);
			incomingRequest.put("UserProfile_userId", userId);
			incomingRequest.put("UserProfile_securityQuestion", newSecurityQuestion);
			incomingRequest.put("UserProfile_securityAnswer", newSecurityAnswer);
			incomingRequest.put("securityAnswer", securityAnswer);
			
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
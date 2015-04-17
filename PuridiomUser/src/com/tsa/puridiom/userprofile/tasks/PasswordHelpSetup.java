package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.exceptions.PasswordSecurityException;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class PasswordHelpSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	securityAnswer = (String) incomingRequest.get("securityAnswer");

			if (Utility.isEmpty(securityAnswer)) {
				throw new PasswordSecurityException("You must answer your current security question!");
			}

			securityAnswer = securityAnswer.toUpperCase().trim();   
						
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
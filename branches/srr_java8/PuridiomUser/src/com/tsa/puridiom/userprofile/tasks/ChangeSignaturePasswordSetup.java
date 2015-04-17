package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.exceptions.PasswordException;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class ChangeSignaturePasswordSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			String	userId = (String) incomingRequest.get("userId");
			String	password = (String) incomingRequest.get("password");
			String	newPassword = (String) incomingRequest.get("newPassword");
			String	confirmPassword = (String) incomingRequest.get("confirmPassword");

			if (Utility.isEmpty(password)) {
				throw new PasswordException("You must enter your current signature password!");
			}
			if (Utility.isEmpty(newPassword)) {
				throw new PasswordException("You must enter a new signature password!");
			}
			if (Utility.isEmpty(confirmPassword)) {
				throw new PasswordException("You must confirm your new signature password!");
			}
			
			password = password.toUpperCase().trim();
			newPassword = newPassword.toUpperCase().trim();
			confirmPassword = confirmPassword.toUpperCase().trim();
			
			if (!newPassword.equalsIgnoreCase(confirmPassword)) {
				throw new PasswordException("The new signature password and confirm signature password did not match!");
			}
			
			password = BlackBox.getEncrypt(password);
			newPassword = BlackBox.getEncrypt(newPassword);
			confirmPassword = BlackBox.getEncrypt(confirmPassword);

			incomingRequest.put("UserProfile_organizationId", Utility.ckNull(organizationId).toUpperCase());
			incomingRequest.put("UserProfile_userId", userId);
			incomingRequest.put("UserProfile_signaturePassword", newPassword);
			incomingRequest.put("password", password);
			
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
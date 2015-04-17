package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class ChangePasswordSetup extends Task
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
			String	mailId = (String) incomingRequest.get("mailId");
			boolean caseSensitive = PropertiesManager.getInstance(organizationId).getProperty("MISC", "PassCaseSensitive", "N" ).equals("Y");

			if (Utility.isEmpty(password)) {
				throw new Exception("You must enter your current password!");
			}
			if (Utility.isEmpty(newPassword)) {
				throw new Exception("You must enter a new password!");
			}
			if (Utility.isEmpty(confirmPassword)) {
				throw new Exception("You must confirm your new password!!");
			}

			if (caseSensitive) {
			    password = password.trim();
				newPassword = newPassword.trim();
				confirmPassword = confirmPassword.trim();
			} else {
				password = password.toUpperCase().trim();
				newPassword = newPassword.toUpperCase().trim();
				confirmPassword = confirmPassword.toUpperCase().trim();
			}

			if (!newPassword.equalsIgnoreCase(confirmPassword)) {
				throw new Exception("Your new password and confirm password do not match!");
			}

			password = BlackBox.getEncrypt(password);
			newPassword = BlackBox.getEncrypt(newPassword);

			incomingRequest.put("UserProfile_organizationId", organizationId);
			incomingRequest.put("UserProfile_userId", userId);
			incomingRequest.put("UserProfile_userPassword", newPassword);
			incomingRequest.put("UserProfile_passChanged", Dates.today("yyyy-MM-dd", ""));

			incomingRequest.put("userLoginId", mailId);
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
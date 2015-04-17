package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.exceptions.PasswordException;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class RegisterUserChangePasswordSetup extends Task
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

			if (Utility.isEmpty(password)) {
				throw new PasswordException("You must enter your current password!");
			}
			if (Utility.isEmpty(newPassword)) {
				throw new PasswordException("You must enter a new password!");
			}
			if (Utility.isEmpty(confirmPassword)) {
				throw new PasswordException("You must confirm your new password!!");
			}

			password = password.toUpperCase().trim();
			newPassword = newPassword.toUpperCase().trim();
			confirmPassword = confirmPassword.toUpperCase().trim();

			if (!newPassword.equalsIgnoreCase(confirmPassword)) {
				throw new PasswordException("Your new password and confirm password do not match!");
			}

			password = BlackBox.getEncrypt(password);
			incomingRequest.put("password", password);
            newPassword = BlackBox.getEncrypt(newPassword);
            incomingRequest.put("newPassword", newPassword);

			RegisterUser registerUser = (RegisterUser) incomingRequest.get("registerUser");

			incomingRequest.put("Contact_contactCode", registerUser.getContactCode());
			incomingRequest.put("Contact_contactType", registerUser.getContactType());
			incomingRequest.put("Contact_vendorId", registerUser.getVendorId());
			incomingRequest.put("Contact_contactPassword", newPassword);
			incomingRequest.put("Contact_passChanged", Dates.today("yyyy-MM-dd", ""));

			incomingRequest.put("VendorRegister_contactEmailAddr", registerUser.getEmailAddress());
			incomingRequest.put("VendorRegister_vendorId", registerUser.getVendorId());
			incomingRequest.put("VendorRegister_contactPassword", newPassword);
			incomingRequest.put("VendorRegister_passChanged", Dates.today("yyyy-MM-dd", ""));

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

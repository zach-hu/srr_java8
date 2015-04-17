package com.tsa.puridiom.supplierportal.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.exceptions.PasswordException;
import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;
import com.tsa.puridiom.property.PropertiesManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BidBoardUserChangePasswordSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			String	newPassword = (String) incomingRequest.get("newPassword");
			String	confirmPassword = (String) incomingRequest.get("confirmPassword");
			String	password = (String) incomingRequest.get("password");
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
			    newPassword 	= newPassword.trim();
				password 		= password.trim();
				confirmPassword = confirmPassword.trim();
			} else {
				newPassword 	= newPassword.toUpperCase().trim();
				password 		= password.toUpperCase().trim();
				confirmPassword = confirmPassword.toUpperCase().trim();
			}

			if (!newPassword.equalsIgnoreCase(confirmPassword)) {
				throw new Exception("Your new password and confirm password do not match!");
			}

			String encNewPassword = BlackBox.getEncrypt(newPassword);
			password = BlackBox.getEncrypt(password);
			incomingRequest.put("password", password);
			incomingRequest.put("newPassword", newPassword);
			incomingRequest.put("confirmPassword", confirmPassword);
			incomingRequest.put("userLoginId", mailId);

			BidBoardUser bidboardUser = (BidBoardUser) incomingRequest.get("bidboardUser");

			incomingRequest.put("Contact_contactCode", bidboardUser.getContactCode());
			incomingRequest.put("Contact_contactType", bidboardUser.getContactType());
			incomingRequest.put("Contact_vendorId", bidboardUser.getVendorId());
			incomingRequest.put("Contact_contactPassword", encNewPassword);
			incomingRequest.put("Contact_passChanged", Dates.today("yyyy-MM-dd", ""));

			incomingRequest.put("VendorRegister_contactEmailAddr", bidboardUser.getEmailAddress());
			incomingRequest.put("VendorRegister_vendorId", bidboardUser.getVendorId());
			incomingRequest.put("VendorRegister_contactPassword", encNewPassword);
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
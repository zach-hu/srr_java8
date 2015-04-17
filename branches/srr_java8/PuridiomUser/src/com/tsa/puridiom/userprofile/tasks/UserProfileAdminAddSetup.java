package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import com.tsa.puridiom.common.utility.HiltonUtility;

import java.util.*;

public class UserProfileAdminAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	password = (String) incomingRequest.get("UserProfile_userPassword");
			String	signaturePassword = (String) incomingRequest.get("UserProfile_signaturePassword");
			String	organizationId = (String) incomingRequest.get("UserProfile_organizationId");
			String	mailId = (String) incomingRequest.get("UserProfile_mailId");
			String	userId = (String) incomingRequest.get("UserProfile_userId");
			String	organization = (String) incomingRequest.get("oid");
			String	userChangeBy = (String) incomingRequest.get("userId");
			String  userbartChart = (String) incomingRequest.get("UserProfile_barChart");
			String	defaultContact = (String) incomingRequest.get("UserProfile_defaultContact");
			String date = (String) HiltonUtility.getFormattedDate(new Date(), organizationId);

			List		userRoleList = new ArrayList();

			// Set HostUser variables for vaildation and to add new HOST_USER record if successful
			incomingRequest.put("HostUser_organizationId", organizationId);
			incomingRequest.put("HostUser_userId", userId);
			incomingRequest.put("HostUser_mailId", mailId);
			incomingRequest.put("UserProfile_lastChangeBy", userChangeBy);
			incomingRequest.put("UserProfile_lastChangeDate", date );
			incomingRequest.put("UserProfile_barChart", userbartChart );
			incomingRequest.put("UserProfile_defaultContact", defaultContact);

			// Set password and newPassword for validation before encryption
			// Set newMailId to validate email address
			incomingRequest.put("newPassword", password);
			incomingRequest.put("confirmPassword", incomingRequest.get("confirmUserPassword"));
			incomingRequest.put("userLoginId", mailId);
            incomingRequest.put("newMailId", mailId);

			if (!Utility.isEmpty(password)) {
				password = password.toUpperCase();
				password = BlackBox.getEncrypt(password);

				incomingRequest.put("UserProfile_userPassword", password);

				if (!incomingRequest.containsKey("UserProfile_passChanged")) {
				    String	currentUserId = Utility.ckNull((String) incomingRequest.get("userId"));
				    String	uid = Utility.ckNull((String) incomingRequest.get("UserProfile_userId"));
				    if (!uid.equals(currentUserId)) {
				        incomingRequest.put("UserProfile_passChanged", "ADMIN");
				    } else {
				        incomingRequest.put("UserProfile_passChanged", Dates.today("yyyy-MM-dd"));
				    }
				}
			}
			else {
				incomingRequest.remove("UserProfile_userPassword");
			}

			if (!Utility.isEmpty(signaturePassword)) {
				signaturePassword = signaturePassword.toUpperCase();
				signaturePassword = BlackBox.getEncrypt(signaturePassword);

				incomingRequest.put("UserProfile_signaturePassword", signaturePassword);
			}
			else {
				incomingRequest.remove("UserProfile_signaturePassword");
			}

			if (!incomingRequest.containsKey("UserProfile_owner")) {
				incomingRequest.put("UserProfile_owner", "PURIDIOMWEB");
			}
			incomingRequest.put("UserProfile_expandBrowse","Y");
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
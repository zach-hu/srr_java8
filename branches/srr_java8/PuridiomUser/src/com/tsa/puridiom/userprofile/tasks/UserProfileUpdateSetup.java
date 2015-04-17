package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.*;

public class UserProfileUpdateSetup extends Task
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
			String	newMailId = (String) incomingRequest.get("newUserProfile_mailId");
			String	userId = (String) incomingRequest.get("UserProfile_userId");
			String	locale = (String) incomingRequest.get("UserProfile_locale");
			String	organization = (String) incomingRequest.get("oid");
			boolean caseSensitive = PropertiesManager.getInstance(organizationId).getProperty("MISC", "PassCaseSensitive", "N" ).equals("Y");
			String	userChangeBy = (String) incomingRequest.get("userId");
			String  userbartChart = (String) incomingRequest.get("UserProfile_barChart");
			String	defaultContact = (String) incomingRequest.get("UserProfile_defaultContact");
			String date = (String) HiltonUtility.getFormattedDate(new Date(), organizationId);

			List		userRoleList = new ArrayList();

			// Set password and newPassword for validation before encryption
			//incomingRequest.put("password", password);
			incomingRequest.put("newPassword", password);
			incomingRequest.put("confirmPassword", incomingRequest.get("confirmUserPassword"));
			incomingRequest.put("userLoginId", mailId);
			incomingRequest.put("UserProfile_lastChangeBy", userChangeBy);
			incomingRequest.put("UserProfile_lastChangeDate", date );
			incomingRequest.put("UserProfile_barChart", userbartChart );
			incomingRequest.put("UserProfile_defaultContact", defaultContact);

			if (!Utility.isEmpty(password)) {
				if (! caseSensitive) {
					password = password.toUpperCase();
				}
				password = BlackBox.getEncrypt(password);

				incomingRequest.put("UserProfile_userPassword", password);

			    String	currentUserId = Utility.ckNull((String) incomingRequest.get("userId"));
			    String	uid = Utility.ckNull((String) incomingRequest.get("UserProfile_userId"));
			    if (!uid.equals(currentUserId)) {
			        incomingRequest.put("UserProfile_passChanged", "ADMIN");
			    } else {
			        incomingRequest.put("UserProfile_passChanged", Dates.today("yyyy-MM-dd"));
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

			if (!Utility.isEmpty(newMailId)) {
				incomingRequest.put("HostUser_mailId", mailId);
				incomingRequest.put("HostUser_organizationId", organizationId);
				incomingRequest.put("HostUser_userId", userId);
				incomingRequest.put("newHostUser_mailId", newMailId);
                incomingRequest.put("newMailId", newMailId); //used for validation
			}
			else {
			    incomingRequest.remove("UserProfile_mailId");
			    incomingRequest.remove("HostUser_mailId");
			    incomingRequest.remove("newUserProfile_mailId");
				incomingRequest.remove("newHostUser_mailId");
			}

			incomingRequest.put("AppRule_userId", userId);
			incomingRequest.put("Country_countryCode", HiltonUtility.ckNull(locale));

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
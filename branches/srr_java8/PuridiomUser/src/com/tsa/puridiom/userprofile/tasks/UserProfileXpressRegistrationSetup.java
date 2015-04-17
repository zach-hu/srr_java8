package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.documents.GeneralStatus;
import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class UserProfileXpressRegistrationSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	companyName = (String) incomingRequest.get("companyName");
			String	termsAccepted = (String) incomingRequest.get("termsAccepted");
			String	organizationId = (String) incomingRequest.get("UserProfile_organizationId");
			String	userId = (String) incomingRequest.get("UserProfile_userId");
			String	mailId = (String) incomingRequest.get("UserProfile_mailId");
			String	password = (String) incomingRequest.get("UserProfile_userPassword");

			userId = userId.toUpperCase();
			organizationId = organizationId.toUpperCase();

			incomingRequest.put("organizationId", organizationId);

			// Set password and newPassword for validation before encryption
			incomingRequest.put("newPassword", password);

			if (!HiltonUtility.isEmpty(password)) {
				password = password.toUpperCase();
				password = BlackBox.getEncrypt(password);
			}

			incomingRequest.put("UserProfile_userPassword", password);
			incomingRequest.put("UserProfile_userId", userId);
			incomingRequest.put("UserProfile_status", GeneralStatus.STATUS_ON_HOLD);
			incomingRequest.put("UserProfile_owner", "PURIDIOM");
			incomingRequest.put("UserProfile_buyer", "Y");
			incomingRequest.put("UserProfile_requisitioner", "Y");
			incomingRequest.put("UserProfile_authorizedBy", "N");
			incomingRequest.put("UserProfile_receiver", "Y");
			incomingRequest.put("UserProfile_approver", "Y");
			incomingRequest.put("UserProfile_overriderr", "Y");
			incomingRequest.put("UserProfile_approvalAmount", "0");
			incomingRequest.put("UserProfile_warrantlAmount", "0");
			incomingRequest.put("UserProfile_vchApp", "N");
			incomingRequest.put("UserProfile_dateEntered", Dates.today("yyyy-MM-dd"));
			
			//Only allow for one account administrator per organization
			incomingRequest.put("UserProfile_userType", "ADMIN"); 
			
			// Force password change upon initial login
			incomingRequest.put("UserProfile_passChanged", "ADMIN");
			// Lock account until billing information is validated
			incomingRequest.put("UserProfile_lockLogin", "Y");

			if (HiltonUtility.ckNull(termsAccepted).equals("Y")) {
				incomingRequest.put("UserProfile_termsAccepted", Dates.today("yyyy-MM-dd"));
			}

			// Set HostUser variables for vaildation and to add new HOST_USER record if successful
			incomingRequest.put("HostUser_organizationId", organizationId);
			incomingRequest.put("HostUser_userId", userId);
			incomingRequest.put("HostUser_mailId", mailId);

			incomingRequest.put("Organization_organizationId", organizationId);
			incomingRequest.put("Organization_organizationName", companyName);
			incomingRequest.put("Organization_dateExpires", Dates.add(Dates.today("yyyy-MM-dd"), 30, "yyyy-MM-dd"));

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
package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.BlackBox;
import com.tsa.puridiom.common.documents.GeneralStatus;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.*;

public class UserProfileAccountSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	organizationId = (String) incomingRequest.get("UserProfile_organizationId");
			String	mailId = (String) incomingRequest.get("UserProfile_mailId");
			String	userId = (String) incomingRequest.get("UserProfile_userId");

			// Set HostUser variables for vaildation and to add new HOST_USER record if successful
			incomingRequest.put("HostUser_organizationId", organizationId);
			incomingRequest.put("HostUser_userId", userId.toUpperCase());
			incomingRequest.put("HostUser_mailId", mailId);

			// Set values for database configuration
			incomingRequest.put("organizationId", organizationId);

			incomingRequest.put("UserProfile_userId", userId.toUpperCase());
			incomingRequest.put("UserProfile_status", GeneralStatus.STATUS_ON_HOLD);
			incomingRequest.put("UserProfile_owner", "PURIDIOM");
			incomingRequest.put("UserProfile_buyer", "N");
			incomingRequest.put("UserProfile_requisitioner", "N");
			incomingRequest.put("UserProfile_authorizedBy", "N");
			incomingRequest.put("UserProfile_receiver", "Y");
			incomingRequest.put("UserProfile_approver", "N");
			incomingRequest.put("UserProfile_overriderr", "N");
			incomingRequest.put("UserProfile_approvalAmount", "0");
			incomingRequest.put("UserProfile_warrantlAmount", "0");
			incomingRequest.put("UserProfile_vchApp", "N");

/*
			String	password = (String) incomingRequest.get("UserProfile_userPassword");

			List		userRoleList = new ArrayList();

			// Set password and newPassword for validation before encryption
            // Set newMailId for validation
			incomingRequest.put("newPassword", password);
			incomingRequest.put("userLoginId", mailId);
            incomingRequest.put("newMailId", mailId);

			// Set values for database configuration, and automatic login after successful registration
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("userId", userId.toUpperCase());
			incomingRequest.put("mailId", mailId);

			if (!Utility.isEmpty(password)) {
				password = password.toUpperCase();
				password = BlackBox.getEncrypt(password);
			}

			incomingRequest.put("UserProfile_userPassword", password);
			incomingRequest.put("UserProfile_passChanged", Dates.today("yyyy-MM-dd", ""));
			incomingRequest.put("UserProfile_dateEntered", Dates.today("yyyy-MM-dd", ""));

			userRoleList.add("NEWUSER");
			incomingRequest.put("UserGroupRel_groupId", "NEWUSER");
			incomingRequest.put("UserGroupRel_userId", userId.toUpperCase());
			incomingRequest.put("userRoleList", userRoleList);
*/

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
package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.usermanager.UserManager;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

public class UserProfileDeleteSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	mailId = (String) incomingRequest.get("UserProfile_mailId");
			String	userId = (String) incomingRequest.get("UserProfile_userId");
			String	organizationId = (String) incomingRequest.get("UserProfile_organizationId");

			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId) && Utility.isEmpty(mailId)) {
				UserProfile userToDelete = UserManager.getInstance().getUser(organizationId, userId);
				mailId = userToDelete.getMailId();
			}
			if (!Utility.isEmpty(mailId)) {
				incomingRequest.put("HostUser_mailId", mailId);
			}
			if (!Utility.isEmpty(userId)) {
				incomingRequest.put("AppRule_userId", userId);
			}
			if (!Utility.isEmpty(organizationId)) {
				incomingRequest.put("Organization_organizationId", organizationId);
			}

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
package com.tsa.puridiom.hostuser.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class HostUserSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			HostUser hostUser = (HostUser) incomingRequest.get("hostUser");
			if (hostUser == null)
			{
				hostUser = new HostUser();
			}

			if (incomingRequest.containsKey("HostUser_mailId"))
			{
				String mailId = (String ) incomingRequest.get("HostUser_mailId");
				if (!Utility.isEmpty(mailId)) {
				    mailId = mailId.toLowerCase();
				}
				hostUser.setMailId(mailId);
			}
			if (incomingRequest.containsKey("HostUser_userId"))
			{
				String userId = (String ) incomingRequest.get("HostUser_userId");
				hostUser.setUserId(userId);
			}
			if (incomingRequest.containsKey("HostUser_organizationId"))
			{
				String organizationId = (String ) incomingRequest.get("HostUser_organizationId");
				hostUser.setOrganizationId(organizationId);
			}

			result = hostUser;
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
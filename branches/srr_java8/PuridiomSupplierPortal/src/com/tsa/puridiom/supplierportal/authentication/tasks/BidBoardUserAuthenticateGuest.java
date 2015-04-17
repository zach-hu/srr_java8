package com.tsa.puridiom.supplierportal.authentication.tasks;

import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.*;

public class BidBoardUserAuthenticateGuest extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BidBoardUser user = null;
			String	userId = (String) incomingRequest.get("userId");
			String	organizationId = (String) incomingRequest.get("organizationId");

			if (!Utility.isEmpty(organizationId) && !Utility.isEmpty(userId) && userId.equals("BB-GUEST"))
			{
				user = new BidBoardUser();
				user.setEmailAddress(userId);
				user.setOrganization(organizationId);
				user.setGuest(true);
				user.setAuthenticated(true);
				user.setLoginAttempts(0);
				user.setLoginTime(new Date());
				user.setStatus("02");
			}

			result = user;
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
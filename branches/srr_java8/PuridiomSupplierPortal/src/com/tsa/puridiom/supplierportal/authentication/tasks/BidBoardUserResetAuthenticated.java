package com.tsa.puridiom.supplierportal.authentication.tasks;

import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class BidBoardUserResetAuthenticated extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BidBoardUser user = (BidBoardUser) incomingRequest.get("bidboardUser");
			
			if (user != null)
			{
				user.setAuthenticated(false);
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
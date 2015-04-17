package com.tsa.puridiom.supplierportal.authentication.tasks;

import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsa.puridiom.supplierportal.BidBoardUserManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class BidBoardUserGetFromCache extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String userId = (String) incomingRequest.get("userId");
			String organizationId = (String) incomingRequest.get("organizationId");
			
			BidBoardUser user = BidBoardUserManager.getInstance().getUserInCache(organizationId, userId);

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

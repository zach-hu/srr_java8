package com.tsa.puridiom.supplierportal.tasks;

import com.tsa.puridiom.supplierportal.BidBoardUser;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class BidBoardUserGetOrganizationId extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BidBoardUser user = (BidBoardUser) incomingRequest.get("bidboardUser");
			String	organizationId = null;
			
			if (user != null)
			{
				organizationId = user.getOrganizationId();
			}
			else if (incomingRequest.containsKey("oid"))
			{
			    organizationId = (String) incomingRequest.get("oid");
			}

			result = organizationId;
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
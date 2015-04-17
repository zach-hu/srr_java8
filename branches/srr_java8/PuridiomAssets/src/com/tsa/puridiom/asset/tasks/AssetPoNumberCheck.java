package com.tsa.puridiom.asset.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class AssetPoNumberCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String Asset_purchaseOrder = (String)incomingRequest.get("Asset_purchaseOrder");
		if(Utility.isEmpty(Asset_purchaseOrder))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Purchase Order Number is necessary to retrieve an asset");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;
	}
}
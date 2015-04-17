package com.tsa.puridiom.asset.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class AssetIcLineKeyCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String Asset_icLineKey = (String)incomingRequest.get("Asset_icLineKey");
		if(Utility.isEmpty(Asset_icLineKey))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Ic Line Key is necessary to retrieve an asset");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;
	}
}
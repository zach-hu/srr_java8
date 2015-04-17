package com.tsa.puridiom.asset.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class AssetTagNumberCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String Asset_tagNumber = (String)incomingRequest.get("Asset_tagNumber");
		if(Utility.isEmpty(Asset_tagNumber))
		{
			Asset_tagNumber = (String)incomingRequest.get("AssetCost_tagNumber");
			if(Utility.isEmpty(Asset_tagNumber))
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Tag Number is necessary to retrieve an asset");
			}
			else
			{
				incomingRequest.put("Asset_tagNumber",Asset_tagNumber);
				this.setStatus(Status.SUCCEEDED);
			}
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;
	}
}
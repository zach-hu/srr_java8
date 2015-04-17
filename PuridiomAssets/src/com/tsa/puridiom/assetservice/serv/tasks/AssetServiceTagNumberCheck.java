package com.tsa.puridiom.assetservice.serv.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.Map;

public class AssetServiceTagNumberCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String AssetService_tagNumber = (String ) incomingRequest.get("AssetService_tagNumber");
		if(Utility.isEmpty(AssetService_tagNumber))
		{
			AssetService_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
			if(Utility.isEmpty(AssetService_tagNumber))
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Tag Number is necessary to retrieve an asset");
			}
			else
			{
				incomingRequest.put("AssetService_tagNumber", AssetService_tagNumber);
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
package com.tsa.puridiom.assetlocation.serv.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.Map;

public class AssetLocationTagNumberCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String AssetLocation_tagNumber = (String ) incomingRequest.get("AssetLocation_tagNumber");
		if(Utility.isEmpty(AssetLocation_tagNumber))
		{
			AssetLocation_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
			if(Utility.isEmpty(AssetLocation_tagNumber))
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Tag Number is necessary to retrieve an asset");
			}
			else
			{
				incomingRequest.put("AssetLocation_tagNumber", AssetLocation_tagNumber);
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
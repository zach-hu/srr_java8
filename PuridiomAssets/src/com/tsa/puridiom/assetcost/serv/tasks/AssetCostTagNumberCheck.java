package com.tsa.puridiom.assetcost.serv.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class AssetCostTagNumberCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String AssetCost_tagNumber = (String ) incomingRequest.get("AssetCost_tagNumber");
		if(Utility.isEmpty(AssetCost_tagNumber))
		{
			AssetCost_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
			if(Utility.isEmpty(AssetCost_tagNumber))
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Tag Number is necessary to retrieve an asset");
			}
			else
			{
				incomingRequest.put("AssetCost_tagNumber", AssetCost_tagNumber);
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
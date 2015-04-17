package com.tsa.puridiom.assetcost.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.Map;

public class AssetCostIdCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String AssetCost_tagNumber = (String ) incomingRequest.get("AssetCost_tagNumber");
		String AssetCost_sequenceNo = (String) incomingRequest.get("AssetCost_sequenceNo");

		if (Utility.isEmpty(AssetCost_sequenceNo))
		{
			BigDecimal temp = (BigDecimal) incomingRequest.get("assetCostLastSequence");
			AssetCost_sequenceNo = temp.toString();
		}
		if (Utility.isEmpty(AssetCost_tagNumber))
		{
			AssetCost_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
		}

		if(Utility.isEmpty(AssetCost_tagNumber) || Utility.isEmpty(AssetCost_sequenceNo))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Tag and Sequence Number are necessaries to retrieve an asset cost");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;

	}
}
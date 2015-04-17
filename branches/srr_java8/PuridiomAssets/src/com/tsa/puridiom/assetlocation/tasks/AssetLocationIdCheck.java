package com.tsa.puridiom.assetlocation.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.Map;

public class AssetLocationIdCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String AssetLocation_tagNumber = (String ) incomingRequest.get("AssetLocation_tagNumber");
		String AssetLocation_sequenceNo = (String) incomingRequest.get("AssetLocation_sequenceNo");
		if (Utility.isEmpty(AssetLocation_sequenceNo))
		{
			BigDecimal temp = (BigDecimal) incomingRequest.get("assetLocationLastSequence");
			AssetLocation_sequenceNo = temp.toString();
		}
		if (Utility.isEmpty(AssetLocation_tagNumber))
		{
			AssetLocation_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
		}
		if(Utility.isEmpty(AssetLocation_tagNumber) || Utility.isEmpty(AssetLocation_sequenceNo))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Tag and Sequence Number are necessaries to retrieve an asset location");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;

	}
}
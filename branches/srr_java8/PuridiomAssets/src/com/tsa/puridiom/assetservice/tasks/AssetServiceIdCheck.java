package com.tsa.puridiom.assetservice.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.Map;

public class AssetServiceIdCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String AssetService_tagNumber = (String ) incomingRequest.get("AssetService_tagNumber");
		String AssetService_sequenceNo = (String) incomingRequest.get("AssetService_sequenceNo");
		if (Utility.isEmpty(AssetService_sequenceNo))
		{
			BigDecimal temp = (BigDecimal) incomingRequest.get("assetServiceLastSequence");
			AssetService_sequenceNo = temp.toString();
		}
		if (Utility.isEmpty(AssetService_tagNumber))
		{
			AssetService_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
		}

		if(Utility.isEmpty(AssetService_tagNumber) || Utility.isEmpty(AssetService_sequenceNo))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Tag and Sequence Number are necessaries to retrieve an asset services");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;

	}
}
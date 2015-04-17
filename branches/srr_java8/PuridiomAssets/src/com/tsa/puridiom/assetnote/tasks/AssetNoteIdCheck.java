package com.tsa.puridiom.assetnote.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.Map;

public class AssetNoteIdCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String AssetNote_tagNumber = (String ) incomingRequest.get("AssetNote_tagNumber");
		String AssetNote_sequenceNo = (String) incomingRequest.get("AssetNote_sequenceNo");
		if (Utility.isEmpty(AssetNote_sequenceNo))
		{
			BigDecimal temp = (BigDecimal) incomingRequest.get("assetNoteLastSequence");
			AssetNote_sequenceNo = temp.toString();
		}
		if (Utility.isEmpty(AssetNote_tagNumber))
		{
			AssetNote_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
		}

		if(Utility.isEmpty(AssetNote_tagNumber) || Utility.isEmpty(AssetNote_sequenceNo))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Tag and Sequence Number are necessaries to retrieve an asset note");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;

	}
}
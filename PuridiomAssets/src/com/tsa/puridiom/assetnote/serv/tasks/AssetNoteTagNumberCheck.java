package com.tsa.puridiom.assetnote.serv.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class AssetNoteTagNumberCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String AssetNote_tagNumber = (String) incomingRequest.get("AssetNote_tagNumber");
		if(Utility.isEmpty(AssetNote_tagNumber))
		{
			AssetNote_tagNumber = (String) incomingRequest.get("Asset_tagNumber");
			if(Utility.isEmpty(AssetNote_tagNumber))
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Tag Number is necessary to retrieve an asset");
			}
			else
			{
				incomingRequest.put("AssetNote_tagNumber", AssetNote_tagNumber);
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
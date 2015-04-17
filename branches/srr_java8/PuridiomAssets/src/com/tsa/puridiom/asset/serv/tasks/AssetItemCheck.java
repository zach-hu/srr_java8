package com.tsa.puridiom.asset.serv.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class AssetItemCheck extends Task
{
	/*
	 * @author EDSAC
	 * verify if an asset exist depending of itemNumber and itemLocation parameters
	 * */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String Asset_itemNumber = (String)incomingRequest.get("Asset_itemNumber");
		String Asset_itemLocation = (String)incomingRequest.get("Asset_itemLocation");

		if(Utility.isEmpty(Asset_itemNumber) || Utility.isEmpty(Asset_itemLocation))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Item Number and Location are necessaries to retrieve an asset");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;
	}
}
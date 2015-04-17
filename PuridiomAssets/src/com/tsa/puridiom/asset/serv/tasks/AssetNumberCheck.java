package com.tsa.puridiom.asset.serv.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class AssetNumberCheck extends Task
{
	/*
	 * @author EDSAC
	 *  verify if there are assets number parameter
	 * */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String Asset_number = (String)incomingRequest.get("Asset_number");

		if(Utility.isEmpty(Asset_number))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Number of Asset is necessary to save assets");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;
	}
}
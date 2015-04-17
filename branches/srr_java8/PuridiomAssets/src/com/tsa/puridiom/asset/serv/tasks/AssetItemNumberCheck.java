package com.tsa.puridiom.asset.serv.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.Map;

public class AssetItemNumberCheck extends Task
{
	/*
	 * @author EDSAC
	 * verify if the assets have the itemNumber
	 * */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String Asset_itemNumber = (String) incomingRequest.get("Asset_itemNumber");

		if(Utility.isEmpty(Asset_itemNumber))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Item Number is necessary to save assets");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;
	}
}
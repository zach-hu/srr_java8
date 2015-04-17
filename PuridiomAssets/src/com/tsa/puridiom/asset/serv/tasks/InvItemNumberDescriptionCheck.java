package com.tsa.puridiom.asset.serv.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class InvItemNumberDescriptionCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		/*
		 * @author EDSAC
		 * verify if exist an Asset_itemNumber and Asset_descriptionLocal
		 */
		Map incomingRequest = (Map)object;
		String Asset_itemNumber = (String)incomingRequest.get("Asset_itemNumber");
		String Asset_description = (String)incomingRequest.get("Asset_descriptionLocal");

		if(Utility.isEmpty(Asset_itemNumber) && Utility.isEmpty(Asset_description))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Item Number or Description are necessaries to retrieve an inventory item");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;
	}
}
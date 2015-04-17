package com.tsa.puridiom.asset.serv.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class AssetStatusCheckNumbers extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		/*@author EDSAC
		 * verify that exist an Asset_itemNumber and Asset_tagNumber
		 * */
		Map incomingRequest = (Map)object;
		String Asset_tagNumber = (String)incomingRequest.get("Asset_tagNumber");
		String Asset_itemNumber = (String)incomingRequest.get("Asset_itemNumber");
		if (Asset_itemNumber==null)
		{
			Asset_itemNumber = (String ) incomingRequest.get("InvItem_itemNumber");
		}

		if(Utility.isEmpty(Asset_itemNumber) && Utility.isEmpty(Asset_tagNumber))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Item Number or Tag Number are necessaries to retrieve an asset");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;
	}
}
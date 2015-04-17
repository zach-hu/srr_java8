package com.tsa.puridiom.asset.serv.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.List;
import java.util.Map;

public class AssetPoLineListCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		/*
		 * @author EDSAC
		 * indicate if there is a poline list
		 * */
		Map incomingRequest = (Map)object;
		List poLineList = (List)incomingRequest.get("poLineList");

		if (poLineList.isEmpty())
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
package com.tsa.puridiom.asset.serv.tasks;

import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetTotals extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		/*
		 *@author EDSAC
		 *return the total quantity for each assetStatus
		 *
		 */
		Map incomingRequest = (Map)object;
		BigDecimal sum = new BigDecimal(0);

		try
		{
			List assetStatusList = (List) incomingRequest.get("assetStatusList");
			for (int i=0; i<assetStatusList.size();i++)
			{
				Object temp[] = (Object[]) assetStatusList.get(i);
				BigDecimal count = (BigDecimal) temp[1];
				//AssetStatus assetStatus = (AssetStatus) assetStatusList.get(i);
				sum = sum.add(count);
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return sum;
	}
}
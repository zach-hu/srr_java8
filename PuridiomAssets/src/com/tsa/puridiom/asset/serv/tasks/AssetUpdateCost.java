package com.tsa.puridiom.asset.serv.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AssetUpdateCost extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		/*
		 * @author EDSAC
		 * get the asset costs parameters from assetCostList 
		 */
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Asset asset = (Asset)incomingRequest.get("asset");
			List assetCostList = (List)incomingRequest.get("assetCostList");
			AssetCost assetCost = new AssetCost();
			BigDecimal cost = new BigDecimal(0);

			for (int i=0; i<assetCostList.size(); i++)
			{
				assetCost = (AssetCost)assetCostList.get(i);
				cost = cost.add(assetCost.getAmount());
			}
			asset.setAssetCost(cost);
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(asset);
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
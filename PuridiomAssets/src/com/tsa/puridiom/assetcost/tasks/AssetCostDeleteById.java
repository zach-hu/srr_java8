package com.tsa.puridiom.assetcost.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class AssetCostDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		AssetCost assetCost = (AssetCost)incomingRequest.get("assetCost");
		if(assetCost == null)
		{
			assetCost = new AssetCost();
		}
		AssetCostSetValuesPK setValues = new AssetCostSetValuesPK();
		setValues.setValues(incomingRequest, assetCost);
		dbs.delete(assetCost);
		this.setStatus(dbs.getStatus()) ;
		return assetCost ;
	}

}
package com.tsa.puridiom.assetcost.serv.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class AssetCostDeleteByTagNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

		List items = (List) incomingRequest.get("assetCostList");
		if (items.size()>0)
		{
			for (int i=0; i < items.size(); i++)
			{
				AssetCost assetCost = (AssetCost) items.get(i);
				dbs.delete(assetCost);
			}
		}
		this.setStatus(dbs.getStatus()) ;
		return null;
	}

}
package com.tsa.puridiom.assetservice.serv.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class AssetServiceDeleteByTagNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		DBSession dbs = (DBSession)incomingRequest.get("dbsession");

		List items = (List) incomingRequest.get("assetServiceList");
		if (items.size()>0)
		{
			for (int i=0; i<items.size(); i++)
			{
				AssetService assetService = (AssetService) items.get(i);
				dbs.delete(assetService);
			}
		}
		this.setStatus(dbs.getStatus());
		return null;
	}

}
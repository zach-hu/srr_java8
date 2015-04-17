package com.tsa.puridiom.assetlocation.serv.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class AssetLocationDeleteByTagNumber extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

		List items = (List)incomingRequest.get("assetLocationList");
		if (items.size()>0)
		{
			for (int i=0; i<items.size(); i++)
			{
				AssetLocation assetLocation = (AssetLocation) items.get(i);
				dbs.delete(assetLocation);
			}
		}
		this.setStatus(dbs.getStatus()) ;
		return null;
	}

}
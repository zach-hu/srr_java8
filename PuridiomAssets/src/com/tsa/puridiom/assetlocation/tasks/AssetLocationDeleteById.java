package com.tsa.puridiom.assetlocation.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class AssetLocationDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		AssetLocation assetLocation = (AssetLocation)incomingRequest.get("assetLocation");
		if(assetLocation == null)
		{
			assetLocation = new AssetLocation();
		}
		AssetLocationSetValuesPK setValues = new AssetLocationSetValuesPK();
		setValues.setValues(incomingRequest, assetLocation);
		dbs.delete(assetLocation);
		this.setStatus(dbs.getStatus()) ;
		return assetLocation ;
	}

}
package com.tsa.puridiom.asset.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class AssetDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Asset asset = (Asset)incomingRequest.get("asset");
		if(asset == null)
		{
			asset = new Asset();
		}
		AssetSetValuesPK setValues = new AssetSetValuesPK();
		setValues.setValues(incomingRequest, asset);
		dbs.delete(asset);
		this.setStatus(dbs.getStatus()) ;
		return asset ;
	}

}
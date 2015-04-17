package com.tsa.puridiom.assetservice.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class AssetServiceDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		AssetService assetService = (AssetService)incomingRequest.get("assetService");
		if(assetService == null)
		{
			assetService = new AssetService();
		}
		AssetServiceSetValuesPK setValues = new AssetServiceSetValuesPK();
		setValues.setValues(incomingRequest, assetService);
		dbs.delete(assetService);
		this.setStatus(dbs.getStatus()) ;
		return assetService ;
	}

}
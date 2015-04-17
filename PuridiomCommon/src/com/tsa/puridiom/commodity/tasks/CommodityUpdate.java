package com.tsa.puridiom.commodity.tasks;

import com.tsa.puridiom.entity.Commodity;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class CommodityUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			Commodity commodity = (Commodity)incomingRequest.get("commodity");
			if (commodity == null)
			{
				throw new Exception ("Commodity was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(commodity);
		
			result = commodity;
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
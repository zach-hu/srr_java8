package com.tsa.puridiom.subcommodity.tasks;

import com.tsa.puridiom.entity.SubCommodity;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class SubCommodityAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
		    SubCommodity subCommodity = (SubCommodity) incomingRequest.get("subCommodity");
			if (subCommodity == null)
			{
				throw new Exception ("SubCommodity was not found.");
			}
		
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
			dbs.add(subCommodity);
		
			result = subCommodity;
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
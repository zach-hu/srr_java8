package com.tsa.puridiom.unitofmeasure.tasks;

import com.tsa.puridiom.entity.UnitOfMeasure;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class UnitOfMeasureUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			UnitOfMeasure unitOfMeasure = (UnitOfMeasure)incomingRequest.get("unitOfMeasure");
			if (unitOfMeasure == null)
			{
				throw new Exception ("UnitOfMeasure was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(unitOfMeasure);
		
			result = unitOfMeasure;
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
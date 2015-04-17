package com.tsa.puridiom.bommanufacturer.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class BomManufacturerAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			BomManufacturer bomManufacturer = (BomManufacturer)incomingRequest.get("bomManufacturer");
			if (bomManufacturer == null)
			{
				throw new Exception ("BomManufacturer was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(bomManufacturer);
		
			result = bomManufacturer;
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
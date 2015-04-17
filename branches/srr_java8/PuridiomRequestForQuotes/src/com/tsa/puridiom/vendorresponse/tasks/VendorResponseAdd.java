package com.tsa.puridiom.vendorresponse.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorResponseAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			VendorResponse vendorResponse = (VendorResponse)incomingRequest.get("vendorResponse");
			if (vendorResponse == null)
			{
				throw new Exception ("VendorResponse was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(vendorResponse);
		
			result = vendorResponse;
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
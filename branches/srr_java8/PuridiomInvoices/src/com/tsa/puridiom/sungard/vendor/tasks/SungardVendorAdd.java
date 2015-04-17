package com.tsa.puridiom.sungard.vendor.tasks;

import com.tsa.puridiom.entity.sungard.Vendor;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class SungardVendorAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			Vendor vendor = (Vendor)incomingRequest.get("sungardVendor");
			if (vendor == null)
			{
				throw new Exception ("Vendor [sungardVendor] was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(vendor);
		
			result = vendor;
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
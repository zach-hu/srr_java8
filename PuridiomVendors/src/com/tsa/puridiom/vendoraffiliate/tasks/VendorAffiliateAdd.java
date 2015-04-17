package com.tsa.puridiom.vendoraffiliate.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorAffiliateAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			VendorAffiliate vendorAffiliate = (VendorAffiliate)incomingRequest.get("vendorAffiliate");
			if (vendorAffiliate == null)
			{
				throw new Exception ("VendorAffiliate was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(vendorAffiliate);
		
			result = vendorAffiliate;
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
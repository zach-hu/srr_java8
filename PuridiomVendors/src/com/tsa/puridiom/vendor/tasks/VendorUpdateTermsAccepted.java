package com.tsa.puridiom.vendor.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

import java.util.Map;

public class VendorUpdateTermsAccepted extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Vendor vendor = (Vendor)incomingRequest.get("vendor");
			if (vendor == null)
			{
				throw new Exception ("Vendor was not found.");
			}

			vendor.setTermsAccepted(Dates.today("yyyy-MM-dd", ""));

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(vendor);

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
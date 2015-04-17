package com.tsa.puridiom.vendorinsurancedefault.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class VendorInsuranceDefaultUpdate extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			VendorInsuranceDefault vendorInsuranceDefault = (VendorInsuranceDefault)incomingRequest.get("vendorInsuranceDefault");
			if (vendorInsuranceDefault == null)
			{
				throw new Exception ("VendorInsurance was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			dbs.update(vendorInsuranceDefault);
		
			result = vendorInsuranceDefault;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}

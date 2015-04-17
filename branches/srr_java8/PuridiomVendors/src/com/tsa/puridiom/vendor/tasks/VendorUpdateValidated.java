package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.rule.ValidationRules;

import java.util.Map;

public class VendorUpdateValidated extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			Vendor vendor = (Vendor)incomingRequest.get("vendor");
			ValidationRules rules = (ValidationRules)incomingRequest.get("rules");
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			if (vendor != null && rules != null && rules.getResult() != 1)
			{
				vendor.setValidated("N");
				dbs.update(vendor);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}

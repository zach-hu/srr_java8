package com.tsa.puridiom.vendoraccount.tasks;

import com.tsa.puridiom.entity.VendorAccount;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorAccountAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorAccount vendorAccount = (VendorAccount) incomingRequest.get("vendorAccount");
			if (vendorAccount == null)
			{
				throw new Exception ("VendorAccount was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			dbs.add(vendorAccount);

			result = vendorAccount;
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